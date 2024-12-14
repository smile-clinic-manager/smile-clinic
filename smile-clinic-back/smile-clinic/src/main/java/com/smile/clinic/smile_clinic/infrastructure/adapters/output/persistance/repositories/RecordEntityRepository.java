package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordEntityRepository extends JpaRepository<MedicalRecordEntryEntity, Long> {
}
