package com.example.EscBackend;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public Record save(Record record) {
        return recordRepository.save(record);
    }

    public Record findById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        recordRepository.deleteById(id);
    }
}
