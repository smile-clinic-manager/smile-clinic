package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.AppointmentEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Long> {

    @Transactional
    @Query("SELECT a FROM AppointmentEntity a WHERE a.user.id = :userId")
    List<AppointmentEntity> findByUserId(@Param("userId") Long userId);

    @Transactional
    @Query("SELECT a FROM AppointmentEntity a WHERE a.patient.id = :patientId")
    List<AppointmentEntity> findByPatientId(@Param("patientId") Long patientId);

    @Transactional
    @Query("SELECT a FROM AppointmentEntity a WHERE a.patient.clinic.id = :clinicId")
    List<AppointmentEntity> findByClinicId(@Param("clinicId") Long clinicId);
}
