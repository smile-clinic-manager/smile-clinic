package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.UserClinicRoleServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.UserServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.UserClinicRolePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.users.UserClinicRole;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClinicRoleService implements UserClinicRoleServicePort {
    private final UserClinicRolePersistancePort userClinicRolePersistancePort;

    @Override
    public void createMultipleUserClinicRole(Long userId, Long clinicId, List<Long> roleIds) {
        for(Long roleId : roleIds){
            boolean recordExists = this.userClinicRolePersistancePort.recordExists(userId, clinicId, roleId);
            if(!recordExists) {
                this.createUserClinicRole(userId, clinicId, roleId); //SOLO EJECUTAMOS SI NO EXISTE YA EN LA DB
            }
        }
    }

    private void createUserClinicRole(Long userId, Long clinicId, Long roleId) {
        try{
            this.userClinicRolePersistancePort.createUserClinicRole(userId, clinicId, roleId);
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
}
