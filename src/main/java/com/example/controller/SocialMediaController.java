package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.entity.Message;
import com.example.exception.InvalidMessageException;
import com.example.service.MessageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {

    private AccountService accountService;
    private MessageService messageService;

    @Autowired
    public SocialMediaController(AccountService accountService, MessageService messageService) {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("register")
    @ResponseStatus(HttpStatus.OK)
    public Account registerAccount(@RequestBody Account account) {
        return accountService.addAccount(account);
    }

    @PostMapping("login")
    @ResponseStatus(HttpStatus.OK)
    public Account loginAccount(@RequestBody Account account) {
        return accountService.getAccount(account);
    }

    @PostMapping("messages")
    @ResponseStatus(HttpStatus.OK)
    public Message createMessage(@RequestBody Message message) {
        return messageService.addMessage(message);
    }

    @GetMapping("messages")
    public @ResponseBody ResponseEntity<List<Message>> getAllMessages() {
        return new ResponseEntity<List<Message>>(messageService.getAllMessages(), HttpStatus.OK);
    }
}
