package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.PreviousDiseasesDTO;

import java.util.List;

public interface PreviousDiseasesPersistancePort {
    List<PreviousDiseases> getByMedicalHistoryId(Long medicalHistoryId);
}
