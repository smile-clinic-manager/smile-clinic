package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import com.smile.clinic.smile_clinic.application.services.TreatmentInstanceService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GeneratedColumn;

import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
public class MedicalRecordEntryFormDTO {
    private String date;
    private String time;
    private Long treatmentId;
    private Long medicalHistoryId;
    private Long userId;
    private String observations;
    private List<String> teethListId;
    private Long medicalRecordId;
}


