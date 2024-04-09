package com.chatserver.Controllers;

import com.chatserver.Entities.Message;
import com.chatserver.Service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@RestController
@RequestMapping("/messages")

public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping
    public String sendMessage(@RequestBody MessageRequest messageRequest) {
        messageService.sendMessage(messageRequest.getSender(), messageRequest.getContent());
        return "Message sent successfully";
    }

    @GetMapping
    public List<Message> getMessages() {
        return messageService.getMessages();
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    // Inner class representing message request
    static class MessageRequest {
        private String sender;
        private String content;

        // Getters and setters
    }
}
