package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.users.Role;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.RoleDTO;

import java.util.List;

public interface RoleServicePort {
    List<Role> findAllRoles();
}
