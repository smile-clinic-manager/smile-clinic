package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MedicalRecordEntryPersistancePort {

    List<MedicalRecordEntry> findAll();
    MedicalRecordEntry findById(Long id);
    MedicalRecordEntry save(MedicalRecordEntry record);
    MedicalRecordEntry update(Long id, MedicalRecordEntry record);
    void delete(MedicalRecordEntry record);

    @Query("SELECT * FROM Medica")
    List<MedicalRecordEntry> findPatientToothMedicalRecords(Long medicalRecordId, Long toothId);
}
