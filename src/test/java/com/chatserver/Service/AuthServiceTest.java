package com.chatserver.Service;

import com.chatserver.Dao.UserRepository;
import com.chatserver.Entities.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
public class AuthServiceTest {
    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void registerUser() {
        String username = "testUser";
        String password = "testPassword";

        authService.registerUser(username, password);

        // Verify that userRepository.save() is called with the correct User object
        verify(userRepository).save(argThat(user -> user.getUsername().equals(username) && user.getPassword().equals(password)));
    }

    @Test
    void authenticateWithCorrectCredentials() {
        String username = "testUser";
        String password = "testPassword";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(userRepository.findByUsername(username)).thenReturn(user);

        assertTrue(authService.authenticate(username, password));
    }

    @Test
    void authenticateWithIncorrectCredentials() {
        String username = "testUser";
        String password = "testPassword";
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        when(userRepository.findByUsername(username)).thenReturn(user);

        // Attempt authentication with incorrect password
        assertFalse(authService.authenticate(username, "wrongPassword"));

        // Attempt authentication with incorrect username
        assertFalse(authService.authenticate("wrongUsername", password));

        // Attempt authentication with both incorrect username and password
        assertFalse(authService.authenticate("wrongUsername", "wrongPassword"));
    }
}


