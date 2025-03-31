package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import com.smile.clinic.smile_clinic.domain.models.users.Role;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicRoleDTO {
    private Long clinicId;
    private String clinic;
    private List<RoleDTO> roles;
}
