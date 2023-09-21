package com.example.service;

import com.example.entity.Account;
import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    MessageRepository messageRepository;
    AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository) {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message addMessage(Message message) {
        Optional<Account> accountOptional = accountRepository.findById(message.getPosted_by());
        if (message.getMessage_text().isBlank()) {
            throw new InvalidMessageException("The message is blank");
        } else if (message.getMessage_text().length() >= 255) {
            throw new InvalidMessageException("The message is too long");
        } else if (!accountOptional.isPresent())
            throw new InvalidMessageException("The message was not written by a valid account");
        
        return messageRepository.save(message);
    }

    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    public Message getMessageById(int message_id) {
        Optional<Message> messageOptional = messageRepository.findById(message_id);
        if (messageOptional.isPresent()) {
            return messageOptional.get();
        }

        return null;
    }
}
