package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RoleDTO {
    private Long id;
    private String name;
}
