package com.smile.clinic.smile_clinic.domain.models.users;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.domain.models.appointments.Appointment;
import com.smile.clinic.smile_clinic.domain.models.users.roles.Role;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.AppointmentEntity;
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
    private Role role;
    private String password;
    // Relationships
    private List<Appointment> appointments;
    private UserClinicRole userClinicRole;
}
