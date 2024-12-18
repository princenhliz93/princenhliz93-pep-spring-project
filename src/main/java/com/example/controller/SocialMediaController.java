package com.example.controller;

import com.example.service.*;
import com.example.entity.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.context.ApplicationContext;


@RestController
@ResponseBody
@RequestMapping
public class SocialMediaController {

    // Injecting the MessageService to handle message-related operations.
    @Autowired
    MessageService messageService ;

    // Injecting the AccountService to handle account-related operations.
    @Autowired
    AccountService accountService;
    
   /**
     * Retrieves a message by its ID.
     * 
     * @param id The ID of the message.
     * @return The ResponseEntity containing the message or an empty response body if not found.
     */
    @GetMapping("/messages/{messageId}")
    public ResponseEntity<Message> getMessageByIdEntity(@PathVariable("messageId") Integer id)  {
        
        Message foundMessage = messageService.getMessageById(id);
        
       // return ResponseEntity.ok(foundMessage);
       if(foundMessage==null){
        return ResponseEntity.ok().build();
       }else{
        return ResponseEntity.ok(foundMessage);
       }
          
    }
    
    /**
     * Retrieves all messages posted by a specific user.
     * 
     * @param id The ID of the user (accountId).
     * @return The ResponseEntity containing the list of messages posted by the user.
     */
    @GetMapping("/accounts/{accountId}/messages")
    public ResponseEntity<List<Message>> getAllMessagesByUser (@PathVariable("accountId") Integer id){
        List<Message> messages = messageService.getAllMessagesByUser(id);

        return ResponseEntity.ok(messages);
    }

    /**
     * Updates the text of a message.
     * 
     * @param messageId The ID of the message to update.
     * @param newMessageText The new message text wrapped in a Message object.
     * @return The ResponseEntity indicating success (rows updated) or a client error.
     */
    @PatchMapping("/messages/{messageId}")
    public ResponseEntity<?> updateMessage(@PathVariable Integer messageId, @RequestBody Message newMessageText){
        int updatedMessage = messageService.updateMessage(messageId,newMessageText.getMessageText());
        int rows = 1;
        
        if(updatedMessage==1){
            return ResponseEntity.ok(rows);
        }else{
            return ResponseEntity.status(400).body("Client error");
        } 

    }

    /**
     * Retrieves all messages.
     * 
     * @return The ResponseEntity containing a list of all messages.
     */
    @GetMapping("/messages")
    public ResponseEntity<List<Message>> getAllMessages() {
        List<Message> messages = messageService.getAllMessages();
        return ResponseEntity.ok(messages);
    }

    /**
     * Authenticates a user based on username and password.
     * 
     * @param account The account object containing username and password.
     * @return The ResponseEntity with the logged-in account or an unauthorized error.
     */
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Account account){
        Account loggedInAccount = accountService.login(account.getUsername(),account.getPassword());

        if(loggedInAccount!=null){
            return ResponseEntity.ok(loggedInAccount);
        }else{
            return ResponseEntity.status(401).body("Unauthorized");
        }

    }

    /**
     * Deletes a message by its ID.
     * 
     * @param id The ID of the message to delete.
     * @return The ResponseEntity indicating success (1 row updated) or an empty response if not found.
     */
    @DeleteMapping("/messages/{messageId}")
    public ResponseEntity<?> deleteMessageById (@PathVariable("messageId") int id){

        int deleted = messageService.deleteMessageById(id);
        if(deleted == 1){
           // .out.Systemprint("id"+id);
            return ResponseEntity.ok(1);
        }else{
            return ResponseEntity.ok().build();
        }
    }

    /**
     * Creates a new message.
     * 
     * @param message The message object to create.
     * @return The ResponseEntity containing the created message or a client error.
     */
    @PostMapping("/messages")
    public ResponseEntity<?> createMessage (@RequestBody Message message){
        Message createdMessage = messageService.createMessage(message);

        if(createdMessage!=null){
            return ResponseEntity.ok(createdMessage);
        }else{
            return ResponseEntity.status(400).body("Client error");
        }
    }

    /**
     * Registers a new user account.
     * 
     * @param account The account object containing user details.
     * @return The ResponseEntity containing the saved account or a conflict error.
     */
    @PostMapping("/register")
    public ResponseEntity<?> registerUserEntity( @RequestBody Account account){
        
        Account savedAccount = accountService.createAccount(account);
        
        if(savedAccount!=null){
            return ResponseEntity.ok(savedAccount);
        }else{
            return ResponseEntity.status(409).body("Conflict");
        }
         
        
    }

}
