package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalHistoryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MedicalHistoryEntityRepository extends JpaRepository<MedicalHistoryEntity, Long> {


    @Query(value = "SELECT medical_history.id, medical_history.allergies FROM medical_history " +
            "INNER JOIN patients ON patients.medical_history_id = medical_history.id " +
            "WHERE patients.id = :patientId", nativeQuery = true)
    MedicalHistoryEntity findHistoryByPatientId(@Param("patientId") Long patientId);

    @Modifying
    @Query(value = "INSERT INTO medical_record_entry_tooth (medical_record_entry_id, tooth_id) VALUES (:medicalRecordId, :toothId)", nativeQuery = true)
    void insertToothRelationship(@Param("medicalRecordId") Long medicalRecordId, @Param("toothId") Long toothId);

    @Query(value = "SELECT tooth_id FROM medical_record_entry_tooth where medical_record_entry_id = :medicalRecordId ", nativeQuery = true)
    List<Long> findRelatedTeeth(@Param("medicalRecordId")Long medicalRecordId);

    @Query(value = "SELECT DISTINCT medical_record_entries.id FROM medical_record_entries " +
            "WHERE medical_record_entries.medical_history_id = :medicalHistoryId ", nativeQuery = true)
    List<Long> findMedicalRecordsIdByHistoryId(@Param("medicalHistoryId") Long medicalHistoryId);
}
