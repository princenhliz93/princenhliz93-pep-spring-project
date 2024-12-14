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
//@ResponseBody
@RequestMapping
public class SocialMediaController {

    @Autowired
    MessageService messageService;
    @Autowired
    AccountService accountService;
    
    @GetMapping("/{messageId}")
    public ResponseEntity<?> getMessagEntity(@PathVariable long id){
        Message foundMessage = messageService.getMessageById(id);
        /**  
        if(foundMessage!=null){
            return ResponseEntity.ok(foundMessage);
        }else{
            return ResponseEntity.status(200).body(null);
        }
            */
        return ResponseEntity.status(200).build();
    }
    @PatchMapping("/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable Long messageId, @RequestBody String newMessageText){
        Message updatedMessage = messageService.updateMessage(messageId,newMessageText);
        int rows = 1;
        /**
        if(updatedMessage!=null){
            return ResponseEntity.ok(rows);
        }else{
            return ResponseEntity.status(400).body("Client error");
        }
           */   
        return ResponseEntity.status(400).body("Client error");

    }
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }
    
    @PostMapping("/register")
    public ResponseEntity<?> registerUserEntity( @RequestBody Account account){
        /**
        try{
            Account savedAccount = accountService.createAccount(account);
            return ResponseEntity.ok(savedAccount);
        } catch (IllegalArgumentException e){
            return ResponseEntity.status(400).body("Client error");
        } catch (IllegalStateException e){
            return ResponseEntity.status(409).body("Conflict");
        }
             */
        Account savedAccount = accountService.createAccount(account);
        
        if(savedAccount!=null){
            return ResponseEntity.ok(savedAccount);
        }else{
            return ResponseEntity.status(409).body("Conflict");
        }
        
        
        
    }

}
