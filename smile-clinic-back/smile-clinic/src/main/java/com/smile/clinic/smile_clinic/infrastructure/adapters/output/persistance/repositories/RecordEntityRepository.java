package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.RecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordEntityRepository extends JpaRepository<RecordEntity, Long> {
    List<RecordEntity> findByPatientId(int patientId);

    List<RecordEntity> findByUserId(int userId);

    List<RecordEntity> findByTreatmentIdentifier(String treatmentIdentifier);
}
