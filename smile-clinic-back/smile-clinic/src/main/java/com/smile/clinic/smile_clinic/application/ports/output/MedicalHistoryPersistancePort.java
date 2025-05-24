package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.List;
import java.util.Optional;

public interface MedicalHistoryPersistancePort {
    Optional<MedicalHistory> findById(Long medicalHistoryId);
    MedicalHistory getMedicalHistoryByPatientId(Long patientId);

    MedicalHistory save(MedicalHistory medicalHistory);

    void bindRecordToMedicalHistory(Long medicalHistoryId, MedicalRecordEntry record);

    void insertToothRelationship(Long medicalRecordId, Long toothId);

    List<Long> findRelatedTeeth(Long medicalRecordId);
}
