package com.chatserver.Service;

import com.chatserver.Dao.MessageRepository;
import com.chatserver.Entities.Message;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;
public class MessageServiceTest {
    @Mock
    private MessageRepository messageRepository;

    @InjectMocks
    private MessageService messageService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @ Test
    void sendMessage() {
        // Create a Message object with expected sender and content
        String expectedSender = "testSender";
        String expectedContent = "testContent";

        // Call the sendMessage method
        messageService.sendMessage(expectedSender, expectedContent);

        // Use ArgumentCaptor to capture the Message object passed to save method
        ArgumentCaptor<Message> captor = ArgumentCaptor.forClass(Message.class);
        verify(messageRepository).save(captor.capture());

        // Retrieve the captured Message object
        Message capturedMessage = captor.getValue();

        // Assert that the captured message has the expected sender and content
        assertEquals(expectedSender, capturedMessage.getSender());
        assertEquals(expectedContent, capturedMessage.getContent());

    }

    @Test
    void getMessages() {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1L, "sender1", "content1", 123456789L));
        messages.add(new Message(2L, "sender2", "content2", 123456790L));

        when(messageRepository.findAll()).thenReturn(messages);

        List<Message> retrievedMessages = messageService.getMessages();

        assertEquals(2, retrievedMessages.size());
        assertEquals(messages.get(0), retrievedMessages.get(0));
        assertEquals(messages.get(1), retrievedMessages.get(1));
    }

    @Test
    void deleteMessage() {
        Long messageId = 1L;

        messageService.deleteMessage(messageId);

        verify(messageRepository, times(1)).deleteById(messageId);
    }
}