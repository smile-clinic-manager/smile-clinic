package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;

import java.util.List;

public class MedicalHistoryDTO {
    private Long id;
    private String allergies;
    private List<PreviousDiseasesDTO> previousDiseases;
}
