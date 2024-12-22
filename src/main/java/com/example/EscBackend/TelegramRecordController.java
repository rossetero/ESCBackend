package com.example.EscBackend;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

@RestController
public class TelegramRecordController {

    @Autowired
    private RecordService recordService;

    @Autowired
    private TelegramBotService telegramBotService;

    @PostMapping("/webhook") // Установите вебхук для получения обновлений от Telegram
    public void handleUpdate(@RequestBody Update update) {
        String messageText = update.getMessage().getText();
        Long chatId = update.getMessage().getChat().getId();
        System.out.println("********COMMAND**********"+messageText+"********************");
        if (messageText.equals("/getAllRecords")) {
            List<Record> records = recordService.findAll();
            StringBuilder responseMessage = new StringBuilder("All Records:\n");
            System.out.println("*****RESPONSE*************"+responseMessage+"********************");
            for (Record record : records) {
                responseMessage.append(record.toString()).append("\n"); // Предполагается, что у вас есть метод toString() в Record
            }
            System.out.println("*****RESPONSE*************"+responseMessage+"********************");

            telegramBotService.sendMessage(responseMessage.toString());
        }
    }
}

