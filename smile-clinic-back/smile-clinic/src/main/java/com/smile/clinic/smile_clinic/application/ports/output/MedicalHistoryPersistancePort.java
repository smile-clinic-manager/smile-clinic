package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;

import java.util.List;

public interface MedicalHistoryPersistancePort {

    MedicalHistory getMedicalHistoryByPatientId(Long patientId);
}
