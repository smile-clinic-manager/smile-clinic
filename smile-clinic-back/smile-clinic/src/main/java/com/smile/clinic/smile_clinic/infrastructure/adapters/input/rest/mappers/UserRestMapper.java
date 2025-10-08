package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.SignupRequestDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRestMapper {

    RegisteredUserDTO toRegisteredUserDTO(User user);

    List<RegisteredUserDTO> toRegisteredUserListDTO(List<User> users);

    SignupRequestDTO toSignupRequestDTO(User user);

    User toUser(SignupRequestDTO saveUserDTO);

    User toUser(RegisteredUserDTO registeredUserDTO);

}
