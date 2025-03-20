package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.UserClinicRolePersistancePort;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserClinicRoleEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.UserClinicRoleEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class UserClinicRolePersistanceAdapter implements UserClinicRolePersistancePort {
    private final UserClinicRoleEntityRepository userClinicRoleEntityRepository;
    @Override
    public void createUserClinicRole(Long userId, Long clinicId, Long roleId) {
        this.userClinicRoleEntityRepository.createUserClinicRole(userId, clinicId, roleId);
    }

    public boolean recordExists(Long userId, Long clinicId, Long roleId){
        return this.userClinicRoleEntityRepository.recordExists(userId, clinicId, roleId)!=0;
    }

    @Override
    public void deleteUserClinicRole(Long userId, Long clinicId) {
        this.userClinicRoleEntityRepository.deleteUserClinicRole(clinicId, userId);
    }

    @Override
    public UserClinicRoleEntity findUserClinicRoleByClinicIdUserId(Long userId, Long clinicId) {
        return this.userClinicRoleEntityRepository.findUserClinicRoleByClinicIdUserId(clinicId, userId);
    }
}
