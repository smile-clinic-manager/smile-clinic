package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.TreatmentInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TreatmentInstanceEntityRepository extends JpaRepository<TreatmentInstanceEntity, Long> {

    @Query("SELECT t FROM TreatmentInstanceEntity t WHERE t.patient.id = :patientId")
    List<TreatmentInstanceEntity> findByPatientId(Long patientId);

}
