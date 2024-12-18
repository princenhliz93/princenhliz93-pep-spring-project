package com.example.service;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    
    MessageRepository messageRepository;
    AccountRepository accountRepository;

    // Constructor injection for dependency management.
    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    /**
     * Retrieves a message by its ID.
     * 
     * @param id The ID of the message to retrieve.
     * @return The Message object if found, or null if not found.
     */
    public Message getMessageById(Integer id){

       return messageRepository.findByMessageId(id).orElse(null);

    }

    /**
     * Retrieves all messages posted by a specific user.
     * 
     * @param id The ID of the user.
     * @return A list of Message objects posted by the user.
     */
    public List<Message> getAllMessagesByUser (Integer id){

        return messageRepository.findAllByPostedBy(id);

    }
   
    /**
     * Deletes a message by its ID.
     * 
     * @param id The ID of the message to delete.
     * @return 1 if the message was deleted successfully, 0 otherwise.
     */
    public int deleteMessageById (Integer id) {
     
         if(messageRepository.existsByMessageId(id)){           
            messageRepository.deleteByMessageId(id);
            return 1;
         }      
        
        return 0;      
    }
     
    /**
     * Creates a new message if it passes validation checks.
     * 
     * @param message The Message object to create.
     * @return The created Message object if successful, or null if validation fails.
     */
    public Message createMessage (Message message){
        
        if(!message.getMessageText().isBlank()&&message.getMessageText().length()<=255&&
        accountRepository.findByAccountId(message.getPostedBy()).isPresent()){
            return messageRepository.save(message);
        }
        return null;
    }

     /**
     * Updates the text of an existing message.
     * 
     * @param id The ID of the message to update.
     * @param replacement The new message text.
     * @return 1 if the update was successful, 0 otherwise.
     */
    public int updateMessage(Integer id, String replacement){
        Optional<Message> optionalMessage = messageRepository.findByMessageId(id);
        if(optionalMessage.isPresent()&&replacement != null && !replacement.isBlank() && replacement.length() <= 255){
            Message message = messageRepository.findByMessageId(id).get();
            message.setMessageText(replacement);
            messageRepository.save(message);
            return 1;
        }
        return 0;
    }

    /**
     * Retrieves all messages from the database.
     * 
     * @return A list of all Message objects.
     */
    public List<Message> getAllMessages (){
        return messageRepository.findAll();
    }
}
