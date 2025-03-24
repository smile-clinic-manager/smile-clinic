package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PatientEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PatientEntityRepository extends JpaRepository<PatientEntity, Long> {

    @Transactional
    @Query(value = "SELECT * FROM patients p WHERE p.clinic_id = :clinicId", nativeQuery = true)
    List<PatientEntity> findPatientsByClinicId(Long clinicId);
}
