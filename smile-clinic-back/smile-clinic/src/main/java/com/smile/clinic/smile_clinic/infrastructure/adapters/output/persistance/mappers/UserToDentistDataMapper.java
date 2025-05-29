package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.users.DentistData;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserToDentistDataMapper {
    DentistData toDentistData(User user);
    User toUser(DentistData dentistData);
    List<DentistData> toDentistDataList(List<User> user);
    List<User> toUserList(List<DentistData> dentistData);
}