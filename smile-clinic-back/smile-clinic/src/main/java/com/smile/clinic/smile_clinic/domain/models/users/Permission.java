package com.smile.clinic.smile_clinic.domain.models.users;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Permission {
    private Long id;
    private String name;
}
