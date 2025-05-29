package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DentistDataDTO {
    private Long id;
    private String firstName;
    private String lastName1;
    private String lastName2;
}
