package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalRecordEntryPersistancePort {

    List<MedicalRecordEntry> findAll();
    MedicalRecordEntry findById(Long id);
    MedicalRecordEntry save(MedicalRecordEntry record);
    MedicalRecordEntry update(Long id, MedicalRecordEntry record);
    void delete(MedicalRecordEntry record);

    @Query("SELECT * FROM medical_record_entries " +
            "INNER JOIN medical_record_entry_tooth ON medical_record_entry_tooth.medical_record_entry_id = medical_record_entries.id " +
            "INNER JOIN tooth ON tooth.id = medical_record_entry_tooth.tooth_id " +
            "WHERE medical_record_entries.medical_history_id = :medicalHistoryId " +
            "AND tooth_id = :toothId")
    List<MedicalRecordEntry> findPatientToothMedicalRecords(@Param("medicalHistoryId") Long medicalHistoryId, @Param("toothId") Long toothId);
}
