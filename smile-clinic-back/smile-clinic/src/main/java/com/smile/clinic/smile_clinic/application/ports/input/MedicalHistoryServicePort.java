package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;

public interface MedicalHistoryServicePort {
    MedicalHistory getMedicalHistoryByPatientId(Long patientId);
    MedicalHistory updateMedicalHistory(MedicalHistory medicalHistory) throws ChangeSetPersister.NotFoundException;
}
