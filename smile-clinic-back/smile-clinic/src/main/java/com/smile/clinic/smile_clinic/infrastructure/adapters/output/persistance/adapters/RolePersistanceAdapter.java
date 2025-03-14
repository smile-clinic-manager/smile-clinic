package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;


import com.smile.clinic.smile_clinic.application.ports.output.RolePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.users.Role;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.RoleEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.RolePersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.RoleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RolePersistanceAdapter implements RolePersistancePort {
    private final RolePersistanceMapper roleMapper;
    private final RoleEntityRepository roleRepository;

    @Override
    public List<Role> findRolesUserClinic(Long userId, Long clinicId) {
        List<RoleEntity> roles = this.roleRepository.findRoleUserByClinic(userId, clinicId);
        return this.roleMapper.toRoleList(roles);
    }

    @Override
    public List<Role> findAllRoles() {
        List<RoleEntity> roles = this.roleRepository.findAll();
        return this.roleMapper.toRoleList(roles);
    }
}
