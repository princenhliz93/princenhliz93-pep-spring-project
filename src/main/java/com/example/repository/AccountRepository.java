package com.example.repository;

import com.example.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface AccountRepository extends JpaRepository<Account, Long>{

    Optional<Account> findByUsername(String username);

    Optional<Account> findByAccountId(Integer id);

    Optional<Account> findByUsernameAndPassword(String username, String password);

    
}
