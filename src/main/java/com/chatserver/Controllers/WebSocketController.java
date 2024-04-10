package com.chatserver.Controllers;

import com.chatserver.Entities.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;

import org.springframework.web.bind.annotation.RequestBody;

@Controller
public class WebSocketController {
    private static Logger LOG = org.slf4j.LoggerFactory.getLogger(WebSocketController.class);

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public Message greeting(@RequestBody Message message) {
        LOG.info("Received message: " + message.getContent());
        message.setContent("Hello, " + message.getContent() + "!");
        LOG.info(" message: " + message.getContent());
        return message;
    }

}
