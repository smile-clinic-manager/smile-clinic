package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.SaveUserDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRestMapper {

    RegisteredUserDTO toRegisteredUserDTO(User user);

    SaveUserDTO toSaveUserDTO(User user);

    User toUser(SaveUserDTO saveUserDTO);

    User toUser(RegisteredUserDTO registeredUserDTO);

}
