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

    public Account createAccount (Account account) throws IllegalArgumentException, IllegalStateException{
       /** 
       if(account.getPassword()!=null||account.getPassword().length()<4 ){
            throw new IllegalArgumentException();
       }

       if(account.getUsername()!=null|| account.getUsername().isBlank()){
            throw new IllegalArgumentException();
       }
        */

       if(accountRepository.findByUsername(account.getUsername()).isPresent()){
            return null;
        }
        
        return accountRepository.save(account);
    }

    
}
