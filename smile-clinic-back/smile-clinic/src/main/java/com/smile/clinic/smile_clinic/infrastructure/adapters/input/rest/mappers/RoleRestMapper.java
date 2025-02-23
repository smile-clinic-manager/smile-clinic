package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.users.Role;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.RoleDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.RoleEntity;
import org.mapstruct.Mapper;

import java.util.List;

public interface RoleRestMapper {
    @Mapper(componentModel = "spring")
    public interface RolePersistanceMapper {
        RoleDTO toRoleDTO(Role role);
        List<RoleDTO> toRoleDTOList(List<Role> roles);
        Role toRole(RoleDTO roleDTO);
        List<Role> toRoleList(List<RoleDTO> rolesDTO);
    }
}
