package com.example.EscBackend;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.List;

@Service
public class RecordService {
    @Autowired
    private RecordRepository recordRepository;

    public List<Record> findAll() {
        return recordRepository.findAll();
    }

    public Record save(Record record) {
        ZoneId zoneId = ZoneId.of("GMT+3");
        ZonedDateTime zonedDateTime = ZonedDateTime.now(zoneId);
        LocalDateTime date = zonedDateTime.toLocalDateTime();
        record.setDate(date);
        return recordRepository.save(record);
    }

    public Record findById(Long id) {
        return recordRepository.findById(id).orElse(null);
    }

    public void deleteById(Long id) {
        recordRepository.deleteById(id);
    }

    public List<Record> findLimitedRecords(int limit) {
        Pageable pageable = PageRequest.of(0, limit);
        return recordRepository.findLastRecords(pageable);
    }

//    public void deleteExceptLastN(int limit){
//        recordRepository.deleteExceptLastN(limit);
//    }

    public void deleteAll(){
        recordRepository.deleteAll();
    }
}
