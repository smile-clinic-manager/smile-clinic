package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ClinicEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClinicEntityRepository extends JpaRepository<ClinicEntity, Long> {

    List<Clinic> findByOwnerId(int ownerId);

    <T> Optional<T> findByAddress(String address);
}
