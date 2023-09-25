package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    @Query(value = "UPDATE message SET message_text = :message_text WHERE message_id = :message_id", nativeQuery = true)
    void updateMessage(@Param("message_text") String message_text, @Param("message_id") int message_id);
}
