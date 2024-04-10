package com.chatserver.Controllers;

import com.chatserver.Service.AuthService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest

public class AuthControllerTest {
    @Mock
    private AuthService authService;

    @InjectMocks
    private AuthController authController;

    @Test
    public void testRegisterUser_UserRegisteredSuccessfully() {
        // Arrange
        String username = "testuser";
        String password = "testpassword";
        AuthController.RegisterRequest registerRequest = new AuthController.RegisterRequest(username, password);

        // Act
        ResponseEntity<String> responseEntity = authController.registerUser(registerRequest);

        // Assert
        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals("User registered successfully", responseEntity.getBody());
        Mockito.verify(authService, Mockito.times(1)).registerUser(username, password);
    }

    @Test
    public void testLogin_AuthenticationSuccessful() {
        // Arrange
        String username = "testuser";
        String password = "testpassword";
        AuthController.LoginRequest loginRequest = new AuthController.LoginRequest(username, password);
        Mockito.when(authService.authenticate(username, password)).thenReturn(true);

        // Act
        ResponseEntity<String> responseEntity = authController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Authentication successful", responseEntity.getBody());
    }

    @Test
    public void testLogin_AuthenticationFailed() {
        // Arrange
        String username = "testuser";
        String password = "testpassword";
        AuthController.LoginRequest loginRequest = new AuthController.LoginRequest(username, password);
        Mockito.when(authService.authenticate(username, password)).thenReturn(false);

        // Act
        ResponseEntity<String> responseEntity = authController.login(loginRequest);

        // Assert
        assertEquals(HttpStatus.UNAUTHORIZED, responseEntity.getStatusCode());
        assertEquals("Invalid credentials", responseEntity.getBody());
    }


}
