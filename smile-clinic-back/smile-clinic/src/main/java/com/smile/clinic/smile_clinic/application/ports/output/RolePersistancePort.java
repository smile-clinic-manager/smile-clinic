package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.users.Role;

import java.util.List;

public interface RolePersistancePort {
    List<Role> findRolesUserClinic(Long userId, Long clinicId);

    List<Role> findAllRoles();
}
