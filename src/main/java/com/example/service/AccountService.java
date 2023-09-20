package com.example.service;

import com.example.entity.Account;
import com.example.exception.DuplicateUsernameException;
import com.example.exception.InvalidPasswordException;
import com.example.exception.InvalidUsernameException;
import com.example.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
        } else if (accountRepository.findAccountByUsername(account.getUsername()) != null) {
            throw new DuplicateUsernameException("Username already exists, please choose another one");
        }
        return accountRepository.save(account);
    }

    public ResponseEntity<Account> getAccount(Account account) {
        Account loginAccount = accountRepository.findAccountByUsernameAndPassword(account.getUsername(), account.getPassword());
        if (loginAccount != null) {
            return new ResponseEntity<>(loginAccount, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
    }
}
