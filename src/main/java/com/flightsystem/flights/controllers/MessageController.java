package com.flightsystem.flights.controllers;

import com.flightsystem.flights.dtos.Message;
import com.flightsystem.flights.services.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/**
 * Contact-Us REST Controller implementation
 * @author  Oshri Nuri
 * @version 1.3
 */
@RestController
@RequestMapping("contact-us")
public class MessageController {
    @Autowired MessageService messageService;
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/messages/{id}")
    public Message getMessageById(@PathVariable("id") int id) {
        return messageService.getMessageById(id);
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @GetMapping("/messages")
    public List<Message> getAllMessages() {
        return messageService.getAllMessages();
    }
    /* ------------------------------------------------------------------------------------------------------------------- */
    @PostMapping("/")
    public void sendMessage(@RequestBody Message message) {
        messageService.addMessage(message);
    }
}
