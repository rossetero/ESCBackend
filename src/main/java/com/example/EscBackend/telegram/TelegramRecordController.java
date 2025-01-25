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
        startMessage(messageText);
        wakeUpCall(messageText);
        getRecords(messageText);
        deleteRecords(messageText);
    }

    private void startMessage(String messageText){
        if (messageText.startsWith("/start")) {
            telegramBotService.sendMessage("This is ESC_Admin bot"+
                    "\nCommands:\n /start - to get info\n/wakeup - activate server\n/getRecords N - get N latest records. If no N, then all records are shown\n/deleteRecords - clear database");
        }
    }

    private void wakeUpCall(String messageText){
        if (messageText.startsWith("/wakeup")) {
            recordService.findLimitedRecords(1);
            telegramBotService.sendMessage("Yawn...\n Free server is awake");
        }
    }

    private void deleteRecords(String messageText){
        if (messageText.startsWith("/deleteRecords")) {
            System.out.println(messageText);
            String responseMessage;
            recordService.deleteAll();
            responseMessage = "Data deleted";
            telegramBotService.sendMessage(responseMessage);
        }
    }

    private void getRecords(String messageText){
        if (messageText.startsWith("/getRecords")) {
            System.out.println(messageText);
            Integer limit = extractNumber(messageText);
            System.out.println(limit);
            List<Record> records;
            if(limit!=null){
                records = recordService.findLimitedRecords(limit);
            } else {
                records = recordService.findAll();
            }
            StringBuilder responseMessage = new StringBuilder("All Records:\n");
            for (Record record : records) {
                responseMessage.append(record.toString()).append("\n");
            }

            telegramBotService.sendMessage(responseMessage.toString());
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

