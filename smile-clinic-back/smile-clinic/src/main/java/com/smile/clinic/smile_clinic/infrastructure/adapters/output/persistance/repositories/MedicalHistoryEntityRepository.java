package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
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
}
