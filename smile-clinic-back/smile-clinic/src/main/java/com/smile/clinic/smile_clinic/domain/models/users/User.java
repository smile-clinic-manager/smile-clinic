package com.smile.clinic.smile_clinic.domain.models.users;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.domain.models.users.roles.Role;
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

    //Relations
    private List<Clinic> ownedClinics; //Clinics where User is owner
    private List<Clinic> clinics; // Clinics in which the owner works
}
