package com.smile.clinic.smile_clinic.domain.models.users.roles;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum Role {
    // Roles de prueba
    CLINIC_ADMIN(Arrays.asList(
            Permission.CREATE_CLINIC, Permission.CREATE_DENTIST, Permission.CREATE_PATIENT, Permission.DELETE_APPOINTMENT,
            Permission.CREATE_APPOINTMENT, Permission.UPDATE_PATIENT, Permission.GET_PATIENTS
    )),
    CLINIC_DENTIST(Arrays.asList(
            Permission.CREATE_PATIENT, Permission.CREATE_APPOINTMENT, Permission.UPDATE_PATIENT, Permission.DELETE_APPOINTMENT,
            Permission.GET_PATIENTS
    ));

    private List<Permission> permissions;

    Role (List<Permission> permissions){
        this.permissions = permissions;
    }


}
