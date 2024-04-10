package com.chatserver.Controllers;


import com.chatserver.Entities.Message;
import com.chatserver.Service.MessageService;
import com.chatserver.Controllers.MessageController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
class MessageServiceTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }

    @Test
    void sendMessage() throws Exception {
        String sender = "testSender";
        String content = "testContent";

        mockMvc.perform(post("/messages")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"sender\": \"" + sender + "\", \"content\": \"" + content + "\"}"))
                .andExpect(status().isOk())
                .andExpect(content().string("Message sent successfully"));

        verify(messageService, times(1)).sendMessage(sender, content);
    }

    @Test
    void getMessages() throws Exception {
        List<Message> messages = new ArrayList<>();
        messages.add(new Message(1L, "sender1", "content1", 123456789L));
        messages.add(new Message(2L, "sender2", "content2", 123456790L));

        when(messageService.getMessages()).thenReturn(messages);

        mockMvc.perform(get("/messages"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].sender").value("sender1"))
                .andExpect(jsonPath("$[0].content").value("content1"))
                .andExpect(jsonPath("$[1].sender").value("sender2"))
                .andExpect(jsonPath("$[1].content").value("content2"));

        verify(messageService, times(1)).getMessages();
    }

    @Test
    void deleteMessage() throws Exception {
        Long messageId = 1L;

        mockMvc.perform(delete("/messages/{id}", messageId))
                .andExpect(status().isOk())
                .andExpect(content().string("Message deleted successfully"));

        verify(messageService, times(1)).deleteMessage(messageId);
    }
}
