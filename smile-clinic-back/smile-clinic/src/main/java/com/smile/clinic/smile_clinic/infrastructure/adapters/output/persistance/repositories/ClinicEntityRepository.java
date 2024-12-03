package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ClinicEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.StudentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClinicEntityRepository extends JpaRepository<ClinicEntity, Long> {
    
}
