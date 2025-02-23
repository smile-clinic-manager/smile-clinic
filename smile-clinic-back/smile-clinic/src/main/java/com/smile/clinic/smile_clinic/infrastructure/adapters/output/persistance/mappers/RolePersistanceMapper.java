package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.users.Role;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface RolePersistanceMapper {
    RoleEntity toRoleEntity(Role role);
    List<RoleEntity> toRoleEntityList(List<Role> roles);
    Role toRole(RoleEntity roleEntity);
    List<Role> toRoleList(List<RoleEntity> rolesEntity);
}
