package com.smile.clinic.smile_clinic.domain.models;

import com.smile.clinic.smile_clinic.domain.models.users.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedicalRecordEntry {
    private Long id;
    private LocalDateTime dateTime;
    private String visitPurpose;
    private String observations;
    private User dentist; //Dentist in charge of the intervention
    private Treatment treatment;
}
