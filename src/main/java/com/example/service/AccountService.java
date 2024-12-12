package com.example.service;

import com.example.repository.AccountRepository;
import com.example.entity.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    public Account createAccount (Account account) {
       /** 
       if(account.getPassword()!=null||account.getUsername()!=null||account.getPassword().length()<4 || account.getUsername().isBlank()){
            throw new IllegalArgumentException();
       }
       if(accountRepository.findByUsername(account.getUsername()).isEmpty()){
            throw new IllegalStateException();
        }
        */ 
        return accountRepository.save(account);
    }

    
}
