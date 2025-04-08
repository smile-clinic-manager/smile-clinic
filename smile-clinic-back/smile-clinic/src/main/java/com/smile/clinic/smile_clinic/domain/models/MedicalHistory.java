package com.smile.clinic.smile_clinic.domain.models;

import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalHistory {
    private Long id;
    private String allergies;
    private List<PreviousDiseases> previousDiseases;
    private Patient patient;
}
