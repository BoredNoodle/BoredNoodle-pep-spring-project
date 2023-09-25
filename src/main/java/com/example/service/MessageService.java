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
        Optional<Account> posted_by = accountRepository.findById(message.getPosted_by());
        if (message.getMessage_text().isBlank()) {
            throw new InvalidMessageException("The message is blank");
        } else if (message.getMessage_text().length() >= 255) {
            throw new InvalidMessageException("The message is too long");
        } else if (posted_by.isEmpty())
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

    public int deleteMessage(int message_id) {
        Optional<Message> messageOptional = messageRepository.findById(message_id);
        messageRepository.deleteById(message_id);
        if (messageOptional.isPresent()) {
            return 1;
        }

        return 0;
    }

    public int updateMessage(int message_id, String message_text) {
        Optional<Message> messageOptional = messageRepository.findById(message_id);
        if (messageOptional.isEmpty()) {
            throw new InvalidMessageException("The message id was not found in the database");
        } else if (message_text.isBlank()) {
            throw new InvalidMessageException("The updated message text is blank");
        } else if (message_text.length() >= 255) {
            throw new InvalidMessageException("The updated message text is too long");
        }

        messageRepository.updateMessage(message_text, message_id);
        Optional<Message> updatedMessage = messageRepository.findById(message_id);
        if (updatedMessage.isPresent()) {
            return 1;
        }
        
        throw new InvalidMessageException("The message failed to update");
    }

    public List<Message> getUserMessages(int account_id) {
        return messageRepository.getUserMessages(account_id);
    }
}
