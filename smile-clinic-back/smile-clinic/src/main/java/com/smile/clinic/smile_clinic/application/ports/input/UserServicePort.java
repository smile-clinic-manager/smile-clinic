package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.users.User;

import java.util.List;
import java.util.Map;

//prueba
//prueba

public interface UserServicePort {

    User findById(Long id);
    User findByUsername(String username);
    List<User> findAll();
    Map<User, String> register(User user, String token);
    User update(User user);
    void deleteById(Long id);

}
