package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.AuthenticationProviderPort;
import com.smile.clinic.smile_clinic.application.ports.output.TokenProviderPort;
import com.smile.clinic.smile_clinic.application.ports.output.UserPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationRequest;
import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationResponse;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private AuthenticationProviderPort authenticationProviderPort;

    @Mock
    private UserPersistancePort userPersistancePort;

    @Mock
    private TokenProviderPort tokenProviderPort;

    @InjectMocks
    private AuthService authService;

    private AuthenticationRequest authRequest;
    private User user;

    @BeforeEach
    void setUp() {
        authRequest = new AuthenticationRequest();
        authRequest.setUsername("testuser");
        authRequest.setPassword("password");

        user = new User();
        user.setUsername("testuser");
        user.setPassword("password"); // Usually hashed, here just for example
    }

    @Test
    void login_success_shouldReturnTokens() throws Exception {
        when(userPersistancePort.findUserByUsername("testuser")).thenReturn(Optional.of(user));
        doNothing().when(authenticationProviderPort).authenticate(authRequest);
        when(tokenProviderPort.generateToken(user)).thenReturn("jwt-token");
        when(tokenProviderPort.generateRefreshToken(user)).thenReturn("refresh-token");

        AuthenticationResponse response = authService.login(authRequest);

        assertNotNull(response);
        assertEquals("jwt-token", response.getToken());
        assertEquals("refresh-token", response.getRefreshToken());

        verify(userPersistancePort).findUserByUsername("testuser");
        verify(authenticationProviderPort).authenticate(authRequest);
        verify(tokenProviderPort).generateToken(user);
        verify(tokenProviderPort).generateRefreshToken(user);
    }

    @Test
    void login_userNotFound_shouldThrowException() {
        when(userPersistancePort.findUserByUsername("testuser")).thenReturn(Optional.empty());

        Exception ex = assertThrows(Exception.class, () -> authService.login(authRequest));
        assertEquals("El usuario o la contraseña son incorrectos.", ex.getMessage());

        verify(userPersistancePort).findUserByUsername("testuser");
        verify(authenticationProviderPort, never()).authenticate(any());
        verify(tokenProviderPort, never()).generateToken(any());
        verify(tokenProviderPort, never()).generateRefreshToken(any());
    }

    @Test
    void validateToken_validToken_shouldReturnTrue() {
        String token = "valid-token";
        Claims claims = Mockito.mock(Claims.class);
        when(tokenProviderPort.getAllTokenClaims(token)).thenReturn(claims);

        Boolean result = authService.validateToken(token);

        assertTrue(result);
        verify(tokenProviderPort).getAllTokenClaims(token);
    }

    @Test
    void validateToken_invalidToken_shouldReturnFalse() {
        String token = "invalid-token";
        when(tokenProviderPort.getAllTokenClaims(token)).thenThrow(new RuntimeException("Invalid token"));

        Boolean result = authService.validateToken(token);

        assertFalse(result);
        verify(tokenProviderPort).getAllTokenClaims(token);
    }
}
