package com.flightsystem.flights.services;

import com.flightsystem.flights.dtos.Message;
import com.flightsystem.flights.repositories.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    @Autowired
    MessageRepository messageRepository;
    /* ------------------------------------------------------------------------------------------------------------------- */
    public List<Message> getAllMessages() {
        List<Message> messages = new ArrayList<>();
        messageRepository.findAll().forEach(messages::add);
        return messages;
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    public Message getMessageById(int id) {
        return messageRepository.findById(id).orElse(null);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    public void addMessage(Message message){
        messageRepository.save(message);
    }
}
