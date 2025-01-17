package com.example.EscBackend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/records")
public class RecordController {
    @Autowired
    private RecordService recordService;

    @GetMapping("/getAllRecords")
    public List<Record> getAllRecords() {
        return recordService.findAll();
    }

    @PostMapping("/addRecord")
    public Record createRecord(@RequestBody Record record) {
        return recordService.save(record);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRecord(@PathVariable Long id) {
        recordService.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}

