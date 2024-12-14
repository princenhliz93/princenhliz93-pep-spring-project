package com.example.service;

import com.example.repository.MessageRepository;
import com.example.entity.Message;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    public Message getMessageById(long id){
        Optional<Message> optionalMessage = messageRepository.findById(id);
        if(optionalMessage.isPresent()){
            return optionalMessage.get();
        } else{
            return null;
        }
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


    public MessageService(MessageRepository messageRepository){
        
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages (){
        return messageRepository.findAll();
    }
}
