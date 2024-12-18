package com.example.service;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    AccountRepository accountRepository;

    // Constructor injection for the AccountRepository dependency.
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    /**
     * Creates a new user account.
     * 
     * @param account The account object containing the user's details (e.g., username, password).
     * @return The saved Account object if successfully created, or null if the username already exists.
     */
    public Account createAccount (Account account) {

       if(accountRepository.findByUsername(account.getUsername()).isPresent()){
            return null;
        }
        
        return accountRepository.save(account);
    }

    /**
     * Authenticates a user by checking their username and password.
     * 
     * @param username The username of the user attempting to log in.
     * @param password The password of the user attempting to log in.
     * @return The Account object if authentication is successful, or null if invalid credentials are provided.
     */
    public Account login (String username, String password){
        return accountRepository.findByUsernameAndPassword(username,password).orElse(null);
    }

    
}
