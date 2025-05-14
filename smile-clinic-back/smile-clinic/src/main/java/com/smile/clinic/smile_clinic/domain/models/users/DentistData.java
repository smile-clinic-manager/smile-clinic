package com.smile.clinic.smile_clinic.domain.models.users;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DentistData {
    private Long id;
    private String firstName;
    private String lastName1;
    private String lastName2;
}