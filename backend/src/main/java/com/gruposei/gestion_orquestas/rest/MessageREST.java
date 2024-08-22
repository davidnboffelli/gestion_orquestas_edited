package com.gruposei.gestion_orquestas.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gruposei.gestion_orquestas.model.Message;
import com.gruposei.gestion_orquestas.responses.ApiRequestException;
import com.gruposei.gestion_orquestas.responses.ResponseHandler;
import com.gruposei.gestion_orquestas.service.MessageService;

@RestController
@RequestMapping("/api/messages")
public class MessageREST {

    @Autowired
    private MessageService messageService;

    // @Autowired
    // private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private ResponseHandler responseHandler;

    @PostMapping
    private ResponseEntity<Object> save(@RequestBody Message p){

        try{

            Message temporal = messageService.create(p);
            return responseHandler.generateResponse("000",temporal);
        }
        catch(Exception e){

            throw  new ApiRequestException("002");
        }
    }

    @GetMapping
    private ResponseEntity<Object> getAll(){

        try{

            List<Message> messages = messageService.getAll();
            return responseHandler.generateResponse("000",messages);
        }
        catch(Exception e){

            throw new ApiRequestException("002");
        }
    }
}
