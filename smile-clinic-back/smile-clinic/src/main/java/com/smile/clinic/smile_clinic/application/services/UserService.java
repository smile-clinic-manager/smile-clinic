package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.UserServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.PasswordEncoderPort;
import com.smile.clinic.smile_clinic.application.ports.output.TokenProviderPort;
import com.smile.clinic.smile_clinic.application.ports.output.UserPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.InsecurePasswordException;
import com.smile.clinic.smile_clinic.domain.exceptions.UsernameAlreadyExistsException;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserPersistancePort userPersistancePort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenProviderPort tokenProviderPort;

    private static final String PASSWORD_REGEX = "^(?=.*[A-Z])(?=.*[a-z])(?=.*\\d)(?=.*[\\W_]).{12,}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_REGEX);

    @Override
    public User findById(Long id) {
        return null;
    }

    @Override
    public User findByUsername(String username) {
        Optional<User> user = userPersistancePort.findUserByUsername(username);
        if (user.isEmpty()) {
            throw new NoSuchElementException("No user found with username " + username);
        }
        return user.get();
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public Map<User, String> register(User user, String password) throws Exception {
        // Validate if available username
        if (userPersistancePort.findUserByUsername(user.getUsername()).isPresent()) {
            throw new UsernameAlreadyExistsException("Nombre de usuario ya está siendo utilizado");
        }

        if(!this.isValidPassword(password)){
            throw new InsecurePasswordException("Contraseña no válida, debe contener al menos 12 caracteres, una mayúscula, una minúscula y un caracter especial");
        }

        try{
            // Encode the password and create the User domain model
            String encodedPassword = passwordEncoderPort.encode(password);

            User userToRegister = User.builder()
                    .username(user.getUsername())
                    .firstName(user.getFirstName())
                    .lastName1(user.getLastName2())
                    .lastName2(user.getLastName2())
                    .dni(user.getDni())
                    .email(user.getEmail())
                    .password(encodedPassword)
                    .build();

            User savedUser = userPersistancePort.save(userToRegister);
            String token = tokenProviderPort.generateToken(savedUser);

            return Map.of(savedUser, token);

        } catch (Exception e){
            log.error(e.getMessage());
            throw new Exception("Ha ocurrido un error al registrar el usuario");
        }

    }

    private boolean isValidPassword(String password){
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    @Override
    public User update(User user) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
