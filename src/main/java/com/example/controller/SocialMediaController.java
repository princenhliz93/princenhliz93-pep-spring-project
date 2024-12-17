package com.example.controller;

import com.example.service.*;
import com.example.entity.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.context.ApplicationContext;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
@RequestMapping
public class SocialMediaController {

    @Autowired
    MessageService messageService ;
    @Autowired
    AccountService accountService;
    
   
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageEntity(@PathVariable("messageId") Integer id)  {
        
        Message foundMessage = messageService.getMessageById(id);
        
       // return ResponseEntity.ok(foundMessage);
       if(foundMessage==null){
        return ResponseEntity.ok().build();
       }else{
        return ResponseEntity.ok(foundMessage);
       }
        
        
    }
    
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUser (@PathVariable("accountId") Integer id){
        List<Message> messages = messageService.getAllMessagesByUser(id);

        return ResponseEntity.ok(messages);
    }
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable Integer messageId, @RequestBody Message newMessageText){
        int updatedMessage = messageService.updateMessage(messageId,newMessageText.getMessageText());
        int rows = 1;
        
        if(updatedMessage==1){
            return ResponseEntity.ok(rows);
        }else{
            return ResponseEntity.status(400).body("Client error");
        }
              
      //  return ResponseEntity.status(400).body("Client error");

    }
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }
    @PostMapping("/login")
    public ResponseEntity<?> loginEntity(@RequestBody Account account){
        Account loggedInAccount = accountService.login(account.getUsername(),account.getPassword());

        if(loggedInAccount!=null){
            return ResponseEntity.ok(loggedInAccount);
        }else{
            return ResponseEntity.status(401).body("Unauthorized");
        }

    }
    @PostMapping("/messages")
    public ResponseEntity<?> createMessageEntity (@RequestBody Message message){
        Message createdMessage = messageService.createMessage(message);

        if(createdMessage!=null){
            return ResponseEntity.ok(createdMessage);
        }else{
            return ResponseEntity.status(400).body("Client error");
        }
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
