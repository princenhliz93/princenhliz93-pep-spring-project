package com.example.service;

import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    
    MessageRepository messageRepository;
    AccountRepository accountRepository;
    @Autowired
    private MessageService(MessageRepository messageRepository, AccountRepository accountRepository){
        this.accountRepository = accountRepository;
        this.messageRepository = messageRepository;
    }

    
    public Message getMessageById(Integer id){
       
       return messageRepository.findByMessageId(id).orElse(null);
        

    }

    public List<Message> getAllMessagesByUser (Integer id){

        return messageRepository.findAllByPostedBy(id);

    }
/** 
    public Message deleteMessageById (Integer id){
        if(messageRepository.fi)
    }
        */
    public Message createMessage (Message message){
        

        if(!message.getMessageText().isBlank()&&message.getMessageText().length()<=255&&
        accountRepository.findByAccountId(message.getPostedBy()).isPresent()){
            return messageRepository.save(message);
        }
        return null;

    }
    public Message updateMessage(long id, String replacement){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()&&replacement != null && !replacement.isBlank() && replacement.length() <= 255){
            Message message = optionalMessage.get();
            message.setMessageText(replacement);
            return messageRepository.save(message);

        }
        return null;
    }


  

    public List<Message> getAllMessages (){
        return messageRepository.findAll();
    }
}
