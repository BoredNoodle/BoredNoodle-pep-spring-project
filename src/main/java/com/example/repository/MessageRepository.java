package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Message;

public interface MessageRepository extends JpaRepository<Message, Integer>{
    /**
     * Native query that finds all messages by a given account id from the database.
     * @param account_id an int representing an account id.
     * @return A List of Message objects by the given account id.
     */
    @Query(value = "SELECT * FROM message WHERE posted_by = :account_id", nativeQuery = true)
    List<Message> getUserMessages(@Param("account_id") int account_id);
}
