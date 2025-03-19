package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.UserClinicRoleServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.UserServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.UserClinicRolePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.users.UserClinicRole;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserClinicRoleEntity;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserClinicRoleService implements UserClinicRoleServicePort {
    private final UserClinicRolePersistancePort userClinicRolePersistancePort;

    @Override
    public void createMultipleUserClinicRole(Long userId, Long clinicId, List<Long> roleIds) throws Exception {
        for(Long roleId : roleIds){
            boolean recordExists = this.userClinicRolePersistancePort.recordExists(userId, clinicId, roleId);
            if(!recordExists) {
                this.createUserClinicRole(userId, clinicId, roleId); //SOLO EJECUTAMOS SI NO EXISTE YA EN LA DB
            } else {
                throw new Exception("No se puede añadir el mismo usuario en la misma clínica con el mismo rol");
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

    @Override
    @Transactional
    public void deleteUserClinicRole(Long clinicId, Long userId) {
        try{
            this.userClinicRolePersistancePort.deleteUserClinicRole(userId, clinicId);
            System.out.println("apaapa");
        }catch(Exception exception){
            throw new RuntimeException(exception.getMessage());
        }
    }
}
