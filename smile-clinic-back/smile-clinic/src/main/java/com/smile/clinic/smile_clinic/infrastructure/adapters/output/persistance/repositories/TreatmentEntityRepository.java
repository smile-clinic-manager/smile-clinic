package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.TreatmentEntity;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface TreatmentEntityRepository extends JpaRepository<TreatmentEntity, Long> {

    @Query("SELECT c.treatments FROM ClinicEntity c WHERE c.id = :clinicId")
    List<TreatmentEntity> findByClinicId(Long clinicId);
}
