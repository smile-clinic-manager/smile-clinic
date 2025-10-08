package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserClinicRoleEntity;

import java.util.List;

public interface UserClinicRolePersistancePort {
    void createUserClinicRole(Long userId, Long clinicId, Long roleId);
    boolean recordExists(Long userId, Long clinicId, Long roleId);

    void deleteUserClinicRole(Long userId, Long clinicId);

    UserClinicRoleEntity findUserClinicRoleByClinicIdUserId(Long userId, Long clinicId);
}
