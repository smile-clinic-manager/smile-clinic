package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ClinicEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.projection.ClinicProjection;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ClinicEntityRepository extends JpaRepository<ClinicEntity, Long> {

    Optional<Clinic> findByAddress(String address);

    @Transactional
    @Query(value = "SELECT DISTINCT c.* FROM clinics c " +
            "INNER JOIN user_clinic_role ucr ON ucr.clinic_id = c.clinic_id " +
            "WHERE ucr.user_id = :userId", nativeQuery = true)
    List<ClinicProjection> findAllByUserId(@Param("userId") Long userId);
}
