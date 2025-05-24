package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RecordEntityRepository extends JpaRepository<MedicalRecordEntryEntity, Long> {
    @Query(value = "SELECT DISTINCT medical_record_entries.* FROM medical_record_entries " +
            "WHERE medical_record_entries.medical_history_id = :medicalHistoryId", nativeQuery = true)
    List<MedicalRecordEntryEntity> findAllByMedicalHistory(@Param("medicalHistoryId") Long medicalHistoryId);

}
