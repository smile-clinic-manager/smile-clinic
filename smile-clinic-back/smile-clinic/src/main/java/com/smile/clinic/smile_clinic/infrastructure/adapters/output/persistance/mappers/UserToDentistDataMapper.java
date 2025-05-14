package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.users.DentistData;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserToDentistDataMapper {
    DentistData toDentistData(User user);
    User toUser(DentistData dentistData);
}