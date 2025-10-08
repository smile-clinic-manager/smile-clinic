package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.RoleServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.RolePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.users.Role;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.RoleDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleService implements RoleServicePort {
    private final RolePersistancePort rolePersistancePort;

    @Override
    public List<Role> findAllRoles() {
        return this.rolePersistancePort.findAllRoles();
    }
}
