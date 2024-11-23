package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.Student;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.StudentEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserEntity;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Mapper(componentModel = "spring")
public interface UserPersistanceMapper {

    UserEntity toUserEntity(User user);

    User toUser(UserEntity entity);

    List<User> toUserList(List<UserEntity> entities);

    List<UserEntity> toUserEntityList(List<User> users);
}
