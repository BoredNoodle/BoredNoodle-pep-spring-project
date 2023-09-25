package com.example.service;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidLoginException;
import com.example.exception.InvalidPasswordException;
import com.example.exception.InvalidUsernameException;
import com.example.repository.AccountRepository;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account addAccount(Account account) {
        if (account.getUsername().isBlank()) {
            throw new InvalidUsernameException("Username is blank, please fill out a username");
        } else if (account.getPassword().length() < 4) {
            throw new InvalidPasswordException("Password is too short");
        } else if (accountRepository.findAccountByUsername(account.getUsername()).isPresent()) {
            throw new DuplicateUsernameException("Username already exists, please choose another one");
        }
        return accountRepository.save(account);
    }

    public Account getAccount(Account account) {
        Optional<Account> accountOptional = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (accountOptional.isPresent()) {
            Account loginAccount = accountOptional.get();
            return loginAccount;
        }
        throw new InvalidLoginException("Credentials not found in database");
    }
}
