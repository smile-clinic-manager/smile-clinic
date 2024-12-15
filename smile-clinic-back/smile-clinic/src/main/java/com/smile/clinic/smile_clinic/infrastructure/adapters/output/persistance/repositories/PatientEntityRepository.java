package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PatientEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientEntityRepository extends JpaRepository<PatientEntity, Long> {
}
