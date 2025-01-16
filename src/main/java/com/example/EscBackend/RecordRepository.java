package com.example.EscBackend;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
    // Метод для получения всех записей с лимитом
    @Query(value = "SELECT r FROM Record r")
    List<Record> findLimitedRecords(@Param("limit") int limit);
}

