package com.smile.clinic.smile_clinic.domain.models.users;

import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private Long id;
    private String name;
    private List<Permission> permission;
}
