package com.smile.clinic.smile_clinic.domain.models;

import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ToothEntity;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordEntry {
    private Long id;
    private LocalDateTime dateTime;
    private String observations;
    private User user; //Dentist in charge of the intervention
    private TreatmentInstance treatmentInstance;
    private List<ToothEntity> teeth;
}
