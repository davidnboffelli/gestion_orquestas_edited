package com.gruposei.gestion_orquestas.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gruposei.gestion_orquestas.model.Message;
import com.gruposei.gestion_orquestas.repositories.MessageRepository;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public Message create(Message p){

        return messageRepository.save(p);
    }

    public List<Message> getAll(){

        return messageRepository.findAll();
    }
    
}
