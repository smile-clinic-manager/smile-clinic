package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@RequiredArgsConstructor
public class AssignUserToClinicRequestDTO {
    private Long userId;
    private Long clinicId;
    private List<Long> roleIds;
}
