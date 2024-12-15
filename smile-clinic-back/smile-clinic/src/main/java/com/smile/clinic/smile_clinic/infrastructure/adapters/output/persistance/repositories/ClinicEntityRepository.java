package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicEntityRepository extends JpaRepository<ClinicEntity, Long> {

    List<Clinic> findByOwnerId(int ownerId);

    Optional<Clinic> findByAddress(String address);
}
