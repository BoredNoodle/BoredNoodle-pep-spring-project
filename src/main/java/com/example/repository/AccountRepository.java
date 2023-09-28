package com.example.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Integer>{
    /**
     * Explicitly named query that finds an account by its username.
     * @param username a String username.
     * @return the entity with the given username or Optional#empty() if none found.
     */
    Optional<Account> findAccountByUsername(String username);
    Optional<Account> findAccountByUsernameAndPassword(String username, String password);
}
