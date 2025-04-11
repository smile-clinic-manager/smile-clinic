package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PreviousDiseasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PreviousDiseasesEntityRepository extends JpaRepository<PreviousDiseasesEntity, Long> {

    @Query(value = "SELECT previous_diseases.* FROM previous_diseases " +
            "INNER JOIN medical_history_previous_diseases ON medical_history_previous_diseases.previous_diseases_id = previous_diseases.id " +
            "INNER JOIN medical_history ON medical_history.id = medical_history_previous_diseases.medical_history_id " +
            "WHERE medical_history.id = :medicalHistoryId", nativeQuery = true)
    List<PreviousDiseasesEntity> findByMedicalHistoryId(@Param("medicalHistoryId") Long medicalHistoryId);
}
