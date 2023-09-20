package com.example.service;

import com.example.entity.Message;
// import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    MessageRepository messageRepository;
    // AccountRepository accountRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository /*, AccountRepository accountRepository*/) {
        this.messageRepository = messageRepository;
        // this.accountRepository = accountRepository;
    }

    public ResponseEntity<Message> addMessage(Message message) {
        if (message.getMessage_text().isBlank()) {
            return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
        } else if (message.getMessage_text().length() >= 255) {
            return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
        } 
        // else if (accountRepository.findAccountByAccount_id(message.getPosted_by()) == null) {
        //     return new ResponseEntity<Message>(HttpStatus.BAD_REQUEST);
        // }
        return new ResponseEntity<Message>(messageRepository.save(message), HttpStatus.OK);
    }
}
