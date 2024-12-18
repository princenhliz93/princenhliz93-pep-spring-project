package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long>{

    // Method to find an account by its username.
    Optional<Account> findByUsername(String username);

    // Method to find an account by its unique account ID.
    Optional<Account> findByAccountId(Integer id);

    // Method to find an account by its username and password combination.
    Optional<Account> findByUsernameAndPassword(String username, String password);

    
}
