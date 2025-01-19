package com.example.EscBackend.telegram;

import com.example.EscBackend.Record;
import com.example.EscBackend.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;
import java.util.Scanner;

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
            System.out.println(messageText);
            Integer limit = extractNumber(messageText);
            System.out.println(limit);
            List<Record> records;
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
        Scanner s = new Scanner(str);
        s.next();
        if(s.hasNextInt()){
            return s.nextInt();
        } else {
            return null;
        }
    }
}

