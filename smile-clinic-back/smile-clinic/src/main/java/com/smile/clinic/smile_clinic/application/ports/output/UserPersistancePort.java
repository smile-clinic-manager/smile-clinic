package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.users.User;

import java.util.List;
import java.util.Optional;

public interface UserPersistancePort {
    Optional<User> findUserByUsername(String username);
    Optional<User> findById(Long id);
    Optional<User> findByDNI(String dni);
    List<User> findAll();
    User save(User user);
    void deleteById(Long id);

}
