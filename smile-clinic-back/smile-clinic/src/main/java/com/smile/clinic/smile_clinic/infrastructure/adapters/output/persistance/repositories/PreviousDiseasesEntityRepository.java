package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PreviousDiseasesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PreviousDiseasesEntityRepository extends JpaRepository<PreviousDiseasesEntity, Long> {

}
