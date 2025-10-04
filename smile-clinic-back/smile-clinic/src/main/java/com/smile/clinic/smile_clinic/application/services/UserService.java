package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.UserServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.*;
import com.smile.clinic.smile_clinic.domain.exceptions.InsecurePasswordException;
import com.smile.clinic.smile_clinic.domain.exceptions.UsernameAlreadyExistsException;
import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.domain.models.users.Role;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.domain.models.users.UserClinicRole;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserClinicRoleEntity;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserServicePort {

    private final UserPersistancePort userPersistancePort;
    private final RolePersistancePort rolePersistancePort;
    private final PasswordEncoderPort passwordEncoderPort;
    private final TokenProviderPort tokenProviderPort;

    private final ClinicPersistancePort clinicPersistancePort;
    private final UserClinicRolePersistancePort userClincRolePersistancePort;

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

            Clinic defaultClinic = Clinic.builder()
                    .name("Default clinic")
                    .email("default@mail.com")
                    .address("Lorem ipsum")
                    .phoneNumber("123456789")
                    .postalCode("12345")
                    .treatments(new ArrayList<>())
                    .invitations(new ArrayList<>())
                    .build();

            Clinic savedClinic = clinicPersistancePort.save(defaultClinic);

            userClincRolePersistancePort.createUserClinicRole(savedUser.getId(), savedClinic.getId(), 1L);
            UserClinicRoleEntity ucr = userClincRolePersistancePort.findUserClinicRoleByClinicIdUserId(savedClinic.getId(), savedUser.getId());

            savedUser.setUserClinicRoles(new ArrayList<>(List.of(ucr)));

            String token = tokenProviderPort.generateToken(savedUser);
            String refreshToken = tokenProviderPort.generateRefreshToken(savedUser);

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
    public void deleteById(Long id) {  }

    public List<User> findUsersByClinicId(Long clinicId) {
        List<User> users = this.userPersistancePort.findUsersByClinicId(clinicId);
        //Set the roles of each user
        users.forEach(user -> {
            user.setRoles(this.getUserRoles(user.getId(), clinicId));
        });
        return users;
    }

    @Override
    public User findUserByUserId(Long userId) {
        return this.userPersistancePort.findUserByUserId(userId);
    }

    private List<Role> getUserRoles(Long userId, Long clinicId) {
        return this.rolePersistancePort.findRolesUserClinic(userId, clinicId);
    }
}
