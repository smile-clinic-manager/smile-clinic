package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;

import java.util.List;

public interface MedicalRecordEntryServicePort {
    List<MedicalRecordEntry> findAll();
    MedicalRecordEntry findById(Long id);
    MedicalRecordEntry save(MedicalRecordEntry record);
    MedicalRecordEntry update(Long id, MedicalRecordEntry record);
    void delete(MedicalRecordEntry record);

    List<MedicalRecordEntry> findAllByMedicalHistory(Long medicalHistoryId);
}
