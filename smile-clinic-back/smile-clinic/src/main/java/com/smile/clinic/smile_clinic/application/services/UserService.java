package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.UserServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.PasswordEncoderPort;
import com.smile.clinic.smile_clinic.application.ports.output.TokenProviderPort;
import com.smile.clinic.smile_clinic.application.ports.output.UserPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.UsernameAlreadyExistsException;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.domain.models.users.roles.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserPersistancePort userPersistancePort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenProviderPort tokenProviderPort;

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Map<User, String> register(User user, String password) {
        // Validate if available username
        if (userPersistancePort.findUserByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Username is already taken.");
        }
        // TODO: this.validatePassword(password);

        // Encode the password and create the User domain model
        String encodedPassword = passwordEncoderPort.encode(password);

        User userToRegister = User.builder()
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName1(user.getLastName2())
                .lastName2(user.getLastName2())
                .dni(user.getDni())
                .email(user.getEmail())
                .role(Role.ROLE_CLINIC_ADMIN) // Temporary until we implement the roles logic
                .build();

        User savedUser = userPersistancePort.save(userToRegister, encodedPassword);
        String token = tokenProviderPort.generateToken(savedUser);

        Map<User, String> userTokenMap = new HashMap<>();
        userTokenMap.put(savedUser, token);

        return userTokenMap;
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
