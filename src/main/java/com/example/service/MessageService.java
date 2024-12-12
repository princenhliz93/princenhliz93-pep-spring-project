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

    public MessageService(MessageRepository messageRepository){
        
        this.messageRepository = messageRepository;
    }

    public List<Message> getAllMessages (){
        return messageRepository.findAll();
    }
}
