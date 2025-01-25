package com.example.EscBackend.telegram;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
public class TelegramBotService {

    @Value("${telegram.bot.token}")
    private String botToken;


    @Value("${telegram.user.ids}")
    private String userIdsConfig;

    private final RestTemplate restTemplate = new RestTemplate();

    public void sendMessage(String message) {
        String[] userIds = userIdsConfig.split(",");
        String url = String.format("https://api.telegram.org/bot%s/sendMessage", botToken);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        for (String userId : userIds) {
            String jsonBody = String.format("{\"chat_id\":\"%s\",\"text\":\"%s\"}", userId, message);
            HttpEntity<String> requestEntity = new HttpEntity<>(jsonBody, headers);
            restTemplate.postForObject(url, requestEntity, String.class);
        }
    }

    @PostConstruct
    public void setWebhook() {
        String url = String.format("https://api.telegram.org/bot%s/setWebhook?url=%s/webhook", botToken, "escbackend.onrender.com");
        //String url = String.format("https://api.telegram.org/bot%s/setWebhook?url=%s/webhook", botToken, "internal-unduly-peacock.ngrok-free.app");

        restTemplate.postForObject(url, null, String.class);
    }
}

