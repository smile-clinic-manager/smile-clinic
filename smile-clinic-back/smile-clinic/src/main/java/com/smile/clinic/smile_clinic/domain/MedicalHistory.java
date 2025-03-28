package com.smile.clinic.smile_clinic.domain;

import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistory {
    private Long id;
    private String allergies;
    private String previousDiseases;
    private Patient patient;
}
