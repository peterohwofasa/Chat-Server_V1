package com.chatserver.Service;

import com.chatserver.Dao.MessageRepository;
import com.chatserver.Entities.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    @Autowired
    private MessageRepository messageRepository;

    public void sendMessage(String sender, String content) {
        Message message = new Message();
        message.setSender(sender);
        message.setContent(content);
        message.setTimestamp(System.currentTimeMillis());
        messageRepository.save(message);
    }

    public List<Message> getMessages() {
        return messageRepository.findAll();
    }

    public void deleteMessage(Long id) {
        messageRepository.deleteById(id);
    }
}
