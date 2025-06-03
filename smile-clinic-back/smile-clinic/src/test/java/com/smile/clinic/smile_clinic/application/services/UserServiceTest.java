package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.PasswordEncoderPort;
import com.smile.clinic.smile_clinic.application.ports.output.RolePersistancePort;
import com.smile.clinic.smile_clinic.application.ports.output.TokenProviderPort;
import com.smile.clinic.smile_clinic.application.ports.output.UserPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.InsecurePasswordException;
import com.smile.clinic.smile_clinic.domain.exceptions.UsernameAlreadyExistsException;
import com.smile.clinic.smile_clinic.domain.models.users.Role;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.regex.Matcher;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

    @Mock
    private UserPersistancePort userPersistancePort;

    @Mock
    private RolePersistancePort rolePersistancePort;

    @Mock
    private PasswordEncoderPort passwordEncoderPort;

    @Mock
    private TokenProviderPort tokenProviderPort;

    @InjectMocks
    private UserService userService;

    private final User mockUser = User.builder()
            .id(1L)
            .username("testuser")
            .firstName("Test")
            .lastName1("User")
            .lastName2("Mock")
            .dni("12345678A")
            .email("test@example.com")
            .password("EncryptedPass123!")
            .build();

    @Test
    void findByUsername_whenUserExists_returnsUser() {
        Mockito.when(userPersistancePort.findUserByUsername("testuser"))
                .thenReturn(Optional.of(mockUser));

        User result = userService.findByUsername("testuser");

        assertEquals("testuser", result.getUsername());
    }

    @Test
    void findByUsername_whenUserNotFound_throwsException() {
        Mockito.when(userPersistancePort.findUserByUsername("unknown"))
                .thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> userService.findByUsername("unknown"));
    }

    @Test
    void register_whenUsernameExists_throwsUsernameAlreadyExistsException() {
        Mockito.when(userPersistancePort.findUserByUsername("testuser"))
                .thenReturn(Optional.of(mockUser));

        assertThrows(UsernameAlreadyExistsException.class,
                () -> userService.register(mockUser, "ValidPassword123!"));
    }

    @Test
    void register_whenPasswordInvalid_throwsInsecurePasswordException() {
        Mockito.when(userPersistancePort.findUserByUsername("testuser"))
                .thenReturn(Optional.empty());

        String weakPassword = "short";

        assertThrows(InsecurePasswordException.class,
                () -> userService.register(mockUser, weakPassword));
    }

    @Test
    void register_whenExceptionOccurs_throwsGenericException() {
        Mockito.when(userPersistancePort.findUserByUsername("testuser")).thenReturn(Optional.empty());
        Mockito.when(passwordEncoderPort.encode(Mockito.anyString())).thenReturn("encoded");
        Mockito.when(userPersistancePort.save(Mockito.any())).thenThrow(new RuntimeException("DB error"));

        Exception ex = assertThrows(Exception.class, () -> {
            userService.register(mockUser, "StrongPassword123!");
        });

        assertTrue(ex.getMessage().contains("Ha ocurrido un error"));
    }

    @Test
    void register_whenValid_returnsUserAndToken() throws Exception {
        Mockito.when(userPersistancePort.findUserByUsername("testuser")).thenReturn(Optional.empty());
        Mockito.when(passwordEncoderPort.encode(Mockito.anyString())).thenReturn("encodedPass");
        Mockito.when(userPersistancePort.save(Mockito.any())).thenReturn(mockUser);
        Mockito.when(tokenProviderPort.generateToken(mockUser)).thenReturn("accessToken");
        Mockito.when(tokenProviderPort.generateRefreshToken(mockUser)).thenReturn("refreshToken");

        Map<User, String> result = userService.register(mockUser, "ValidPass123!");

        assertNotNull(result);
        assertEquals("accessToken", result.get(mockUser));
    }

    @Test
    void findUsersByClinicId_returnsUsersWithRoles() {
        List<User> users = List.of(mockUser);
        List<Role> roles = List.of(new Role(1L, "ADMIN", List.of()));

        Mockito.when(userPersistancePort.findUsersByClinicId(1L)).thenReturn(users);
        Mockito.when(rolePersistancePort.findRolesUserClinic(1L, 1L)).thenReturn(roles);

        List<User> result = userService.findUsersByClinicId(1L);

        assertEquals(1, result.size());
        assertEquals("testuser", result.get(0).getUsername());
        assertEquals("ADMIN", result.get(0).getRoles().get(0).getName());
    }

    @Test
    void findUserByUserId_returnsUser() {
        Mockito.when(userPersistancePort.findUserByUserId(1L)).thenReturn(mockUser);

        User result = userService.findUserByUserId(1L);

        assertEquals("testuser", result.getUsername());
    }

}
