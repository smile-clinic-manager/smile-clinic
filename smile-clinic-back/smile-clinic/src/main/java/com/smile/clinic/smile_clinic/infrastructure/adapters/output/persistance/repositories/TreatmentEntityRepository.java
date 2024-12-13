package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.TreatmentEntity;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentEntityRepository extends JpaRepository<TreatmentEntity, Long> {

    Optional<Treatment> findByIdentifier(String identifier);

    List<TreatmentEntity> findByNameContaining(String substring);
    List<TreatmentEntity> findByPriceBetween(double minPrice, double maxPrice);
    List<TreatmentEntity> findIfDescriptionContains(String substring);
}
