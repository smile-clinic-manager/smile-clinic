package com.smile.clinic.smile_clinic.domain.models;

import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentInstance {
    private Long id;
    private String name;
    private Double price;
    private String notes;
    private Patient patient;
}
