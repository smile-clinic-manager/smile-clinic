package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AppointmentEntityRepository extends JpaRepository<AppointmentEntity, Long> {

    @Query("SELECT a FROM AppointmentEntity a WHERE a.user.id = :userId")
    List<AppointmentEntity> findByUserId(Long userId);

    @Query("SELECT a FROM AppointmentEntity a WHERE a.patient.id = :patientId")
    List<AppointmentEntity> findByPatientId(Long patientId);

    @Query("SELECT a FROM AppointmentEntity a WHERE a.patient.clinic.id = :clinicId")
    List<AppointmentEntity> findByClinicId(Long clinicId);
}
