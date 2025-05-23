package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import java.util.List;

public class MedicalRecordEntryFormDTO {
    private String date;
    private String time;
    private Long treatmentId;
    private Long userId;
    private String observations;
    private List<Long> teethListId;
}


