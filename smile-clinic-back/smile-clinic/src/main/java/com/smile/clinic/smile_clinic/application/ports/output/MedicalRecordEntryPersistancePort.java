package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;

import java.util.List;

public interface MedicalRecordEntryPersistancePort {

    List<MedicalRecordEntry> findAll();
    MedicalRecordEntry findById(Long id);
    MedicalRecordEntry save(MedicalRecordEntry record);
    MedicalRecordEntry update(Long id, MedicalRecordEntry record);
    void delete(MedicalRecordEntry record);
}
