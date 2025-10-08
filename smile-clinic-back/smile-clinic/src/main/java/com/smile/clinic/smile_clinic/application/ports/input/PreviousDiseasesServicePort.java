package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;

import java.util.List;

public interface PreviousDiseasesServicePort {
    List<PreviousDiseases> getByMedicalHistoryId(Long medicalHistoryId);

    List<PreviousDiseases> getAllDiseases();
}
