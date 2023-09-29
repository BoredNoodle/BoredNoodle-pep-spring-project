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

    /**
     * Use the MessageRepository to insert a new message into the database.
     * @param message A Message object.
     * @return the newly added message if the insert operation was successful, including 
     *         its message_id.
     * @throws InvalidMessageException if the message text is blank or over 255 characters, or
     *      if the posted_by account id does not belong to an existing account in the database.
     */
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

    /**
     * Use the MessageRepository to retrieve all messages from the database.
     * @return a List containing every Message object from the database.
     */
    public List<Message> getAllMessages() {
        return messageRepository.findAll();
    }

    /**
     * Use the MessageRepository to retrieve a message from the database using its id.
     * @param message_id an int representing a message id.
     * @return the found message if the get operation was successful, or <code>null</code>
     *      if a message was not found.
     */
    public Message getMessageById(int message_id) {
        Optional<Message> messageOptional = messageRepository.findById(message_id);
        if (messageOptional.isPresent()) {
            return messageOptional.get();
        }

        return null;
    }

    /**
     * Use the MessageRepository to delete a message from the database using its id.
     * @param message_id an int representing a message id.
     * @return 1 if the message to be deleted was found, or 0 if the message to be 
     *      deleted was not found.
     */
    public int deleteMessage(int message_id) {
        Optional<Message> messageOptional = messageRepository.findById(message_id);
        if (messageOptional.isPresent()) {
            messageRepository.deleteById(message_id);
            return 1;
        }

        return 0;
    }

    /**
     * Use the MessageRepository to update a message from the database using its id.
     * @param message_id an int representing a message id.
     * @param message_text a String representing the updated message text.
     * @return 1 if the updated operation was successfull.
     * @throws InvalidMessageException if the updated message text is blank or over 255 characters,
     *      or if the message to be updated is not found in the database. 
     */
    public int updateMessage(int message_id, String message_text) {
        Optional<Message> messageOptional = messageRepository.findById(message_id);
        if (message_text.isBlank()) {
            throw new InvalidMessageException("The updated message text is blank");
        } else if (message_text.length() >= 255) {
            throw new InvalidMessageException("The updated message text is too long");
        } else if (messageOptional.isEmpty()) {
            throw new InvalidMessageException("The message id was not found in the database");
        }

        Message updatedMessage = messageOptional.get();
        updatedMessage.setMessage_text(message_text);
        messageRepository.save(updatedMessage);
        return 1;
    }

    public List<Message> getUserMessages(int account_id) {
        return messageRepository.getUserMessages(account_id);
    }
}
