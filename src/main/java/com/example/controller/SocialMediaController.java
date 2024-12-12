package com.example.controller;

import com.example.service.*;
import com.example.entity.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@ResponseBody
@RequestMapping
public class SocialMediaController {

    @Autowired
    MessageService messageService;
    AccountService accountService;
    
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }
    
    @PostMapping("/register")
    public ResponseEntity<Account> registerUserEntity( @RequestBody Account account){
        
        try{
            Account savedAccount = accountService.createAccount(account);
            return ResponseEntity.ok(savedAccount);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body(null);
        } catch (IllegalStateException e){
            return ResponseEntity.status(409).body(null);
        }
        
        
    }

}
