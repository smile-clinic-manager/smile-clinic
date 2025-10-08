package com.smile.clinic.smile_clinic.domain.models.users;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.domain.models.appointments.Appointment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserClinicRoleEntity;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Long id;
    private String firstName;
    private String lastName1;
    private String lastName2;
    private String username;
    private String dni;
    private String email;
    private String password;
    // Relationships

    private List<Clinic> clinics;
    private List<Role> roles;

    private List<UserClinicRoleEntity> userClinicRoles;

}
