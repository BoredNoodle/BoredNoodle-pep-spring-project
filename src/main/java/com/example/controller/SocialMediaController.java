package com.example.controller;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.entity.Message;
import com.example.service.MessageService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
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

    /**
     * Endpoint on {@code POST localhost:8080/register} to register a new account.
     * The {@code @RequestBody} annotation will automatically convert the JSON in the request body into an Account object.
     * @param account The new Account object.
     * @return a {@code ResponseEntity} with the newly registered account and status of {@code 200 OK}.
     */
    @PostMapping("register")
    public ResponseEntity<Account> registerAccount(@RequestBody Account account) {
        return new ResponseEntity<Account>(accountService.addAccount(account), HttpStatus.OK);
    }

    /**
     * Endpoint on {@code POST localhost:8080/login} to login an account.
     * The {@code @RequestBody} annotation will automatically convert the JSON in the request body into an Account object.
     * @param account An Account object.
     * @return a {@code ResponseEntity} with the newly registered account and status of {@code 200 OK}.
     */
    @PostMapping("login")
    public ResponseEntity<Account> loginAccount(@RequestBody Account account) {
        return new ResponseEntity<Account>(accountService.getAccount(account), HttpStatus.OK);
    }

    /**
     * Endpoint on @code POST localhost:8080/messages} to create a new message.
     * The {@code @RequestBody} annotation will automatically convert the JSON in the request body into an Message object.
     * @param message A Message object.
     * @return a {@code ResponseEntity} with the newly made message and status of {@code 200 OK}.
     */
    @PostMapping("messages")
    public ResponseEntity<Message> createMessage(@RequestBody Message message) {
        return new ResponseEntity<Message>(messageService.addMessage(message), HttpStatus.OK);
    }

    /**
     * Endpoint on {@code GET localhost:8080/messages} to retrieve all messages from the database.
     * @return a {@code ResponseEntity} with a List of every Message object from the database and status of {@code 200 OK}.
     */
    @GetMapping("messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        return new ResponseEntity<List<Message>>(messageService.getAllMessages(), HttpStatus.OK);
    }

    /**
     * Endpoint on {@code GET localhost:8080/messages/(message_id)} to create a new message.
     * The {@code @PathVariable} annotation will automatically parse the message id from the PATH parameter.
     * @param message_id An int representing a message_id.
     * @return a {@code ResponseEntity} with the found message and status of {@code 200 OK}.
     */
    @GetMapping("messages/{message_id}")
    public ResponseEntity<Message> getMessageById(@PathVariable int message_id) {
        return new ResponseEntity<Message>(messageService.getMessageById(message_id), HttpStatus.OK);
    }

    /**
     * Endpoint on {@code DELETE localhost:8080/messages/(message_id)} to delete a message from the database.
     * The {@code @PathVariable} annotation will automatically parse the message id from the PATH parameter.
     * @param message_id An int representing a message_id.
     * @return a {@code ResponseEntity} with the number of deleted messages and status of {@code 200 OK}.
     */
    @DeleteMapping("messages/{message_id}")
    public ResponseEntity<Integer> deleteMessage(@PathVariable int message_id) {
        return new ResponseEntity<Integer>(messageService.deleteMessage(message_id), HttpStatus.OK);
    }

    /**
     * Endpoint on{@code PATCH localhost:8080/messages/(message_id)}to update a message from the database.
     * The {@code @PathVariable} annotation will automatically parse the message id from the PATH parameter.
     * The {@code @RequestBody} annotation will automatically convert the JSON in the request body into an Message object. 
     * The message text will be pulled from the Message object with the Message method {@code .getMessage_text()}.
     * @param message_id An int representing a message_id.
     * @param message A Message object.
     * @return a {@code ResponseEntity} with the number of updated messages and status of {@code 200 OK}.
     */
    @PatchMapping("messages/{message_id}")
    public ResponseEntity<Integer> updateMessage(@PathVariable int message_id, @RequestBody Message message) {
        return new ResponseEntity<Integer>(messageService.updateMessage(message_id, message.getMessage_text()), HttpStatus.OK);
    }

    /**
     * Endpoint on{@code GET localhost:8080/accounts/(account_id)/messages}to retrieve all messages by an account 
     * from the database.
     * The {@code @PathVariable} annotation will automatically parse the message id from the PATH parameter.
     * @return a{@code ResponseEntity}with a List of every Message object by a given acocunt 
     *      from the database and status of {@code 200 OK}.
     */
    @GetMapping("accounts/{account_id}/messages")
    public ResponseEntity<List<Message>> getUserMessages(@PathVariable int account_id) {
        return new ResponseEntity<List<Message>>(messageService.getUserMessages(account_id), HttpStatus.OK);
    }
}
