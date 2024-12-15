package com.smile.clinic.smile_clinic.domain.models.patients;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Disease {
    private Long id;
    private String name;
}
