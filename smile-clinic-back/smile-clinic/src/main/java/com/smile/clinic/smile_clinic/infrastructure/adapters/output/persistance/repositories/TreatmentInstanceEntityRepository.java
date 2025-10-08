package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.TreatmentEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.TreatmentInstanceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface TreatmentInstanceEntityRepository extends JpaRepository<TreatmentInstanceEntity, Long> {

    @Query("SELECT t FROM TreatmentInstanceEntity t WHERE t.patient.id = :patientId")
    List<TreatmentInstanceEntity> findByPatientId(Long patientId);

    @Query("SELECT t FROM TreatmentEntity t WHERE t.id = :treatmentToCopyId")
    TreatmentEntity findTreatmentToCopy(@Param("treatmentToCopyId") Long treatmentToCopyId);

    @Transactional
    @Modifying
    @Query("DELETE FROM TreatmentInstanceEntity t WHERE t.patient.id = :patientId")
    void deleteByPatientId(@Param("patientId") Long id);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM MedicalRecordEntryEntity m WHERE m.treatmentInstance.id = :treatmentInstanceId")
    void deleteMedicalRecordsByTreatmentInstanceId(@Param("treatmentInstanceId") Long id);
}
