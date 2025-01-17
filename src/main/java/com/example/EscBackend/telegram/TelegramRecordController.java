package com.example.EscBackend.telegram;

import com.example.EscBackend.Record;
import com.example.EscBackend.RecordService;
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

    @PostMapping("/webhook")
    public void handleUpdate(@RequestBody Update update) {
        String messageText = update.getMessage().getText();
        Long chatId = update.getMessage().getChat().getId();
        if (messageText.startsWith("/getRecords")) {
            Integer limit = extractNumber(messageText);
            List<com.example.EscBackend.Record> records;
            if(limit!=null){
                records = recordService.findLimitedRecords(limit); //TODO протестировать на ноуте через впн
            } else {
                records = recordService.findAll();
            }
            StringBuilder responseMessage = new StringBuilder("All Records:\n");
            for (Record record : records) {
                responseMessage.append(record.toString()).append("\n");
            }

            telegramBotService.sendMessage(responseMessage.toString());
            //получить вчеращние и сегодняшние записи
        }
    }

    private Integer extractNumber(String str) {
        str = str.trim();
        int lastSpaceIndex = str.lastIndexOf(' ');
        if (lastSpaceIndex != -1 && lastSpaceIndex < str.length() - 1) {
            String numberPart = str.substring(lastSpaceIndex + 1);
            try {
                return Integer.parseInt(numberPart);
            } catch (NumberFormatException e) {
                return null;
            }
        }
        return null;
    }
}

