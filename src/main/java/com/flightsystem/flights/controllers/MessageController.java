package com.flightsystem.flights.controllers;

import com.flightsystem.flights.dtos.Message;
import com.flightsystem.flights.services.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RequiredArgsConstructor
@RestController
@RequestMapping("contact-us")
public class MessageController {
    private final MessageService messageService;
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
