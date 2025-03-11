package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;

import java.util.List;
import java.util.Map;

public interface UserServicePort {

    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    Map<User, String> register(User user, String token) throws Exception;
    User update(User user);
    void deleteById(Long id);
    List<User> findUsersByClinicId(Long id);
    User findUserByUserId(Long id);
}
