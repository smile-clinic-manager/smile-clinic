package com.smile.clinic.smile_clinic.domain.models.users;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.domain.models.users.roles.Role;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserClinicRole {
    private Long id;
    private User user;
    private Clinic clinic;
    private Role role;
}
