package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance;

import com.smile.clinic.smile_clinic.application.ports.output.UserPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.UserPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class UserPersistanceAdapter implements UserPersistancePort {

    private final UserEntityRepository userRepository;
    private final UserPersistanceMapper mapper;

    @Override
    public Optional<User> findUserByUsername(String username) {
        return this.userRepository.findUserByUsername(username).map(mapper::toUser);
    }

    @Override
    public Optional<User> findById(Long id) {
        return this.userRepository.findById(id).map(this.mapper::toUser);
    }

    @Override
    public Optional<User> findByDNI(String dni) {
        return this.userRepository.findUserByDNI(dni).map(this.mapper::toUser);
    }

    @Override
    public List<User> findAll() {
        return this.mapper.toUserList(this.userRepository.findAll());
    }

    @Override
    public User save(User user, String encodedPassword) {
        UserEntity userToSave = this.mapper.toUserEntity(user);
        userToSave.setPassword(encodedPassword);

        return this.mapper.toUser(this.userRepository.save(userToSave));
    }

    @Override
    public void deleteById(Long id) {
        this.userRepository.deleteById(id);
    }
}
