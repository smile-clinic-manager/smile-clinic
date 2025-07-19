package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.MedicalHistoryPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalHistoryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalHistoryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.MedicalHistoryPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.MedicalRecordEntryPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.PatientPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.MedicalHistoryEntityRepository;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.PatientEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class MedicalHistoryPersistanceAdapter implements MedicalHistoryPersistancePort {

    private final MedicalHistoryEntityRepository medicalHistoryEntityRepository;
    private final MedicalHistoryPersistanceMapper medicalHistoryPersistanceMapper;
    private final MedicalRecordEntryPersistanceMapper medicalRecordEntryPersistanceMapper;

    @Override
    public Optional<MedicalHistoryEntity> findById(Long medicalHistoryId) {
        return this.medicalHistoryEntityRepository.findById(medicalHistoryId);
    }

    @Override
    public MedicalHistory getMedicalHistoryByPatientId(Long patientId) {
        MedicalHistory medicalHistories = this.medicalHistoryPersistanceMapper.toMedicalHistory(
                this.medicalHistoryEntityRepository.findHistoryByPatientId(patientId)
        );
        return medicalHistories;
    }

    @Override
    public MedicalHistory save(MedicalHistory medicalHistory) {
        return medicalHistoryPersistanceMapper.toMedicalHistory(
                this.medicalHistoryEntityRepository.save(medicalHistoryPersistanceMapper.toMedicalHistoryEntity(medicalHistory))
        );
    }

    @Override
    public void bindRecordToMedicalHistory(Long medicalHistoryId, MedicalRecordEntry record) {
        MedicalRecordEntryEntity recordEntity = medicalRecordEntryPersistanceMapper.toMedicalRecordEntryEntity(record);
        MedicalHistoryEntity medicalHistoryEntity = this.medicalHistoryEntityRepository.findById(medicalHistoryId).orElseThrow();

        List<MedicalRecordEntryEntity> records = medicalHistoryEntity.getMedicalRecordEntries();
        records.add(recordEntity);
        medicalHistoryEntity.setMedicalRecordEntries(records);
        this.medicalHistoryEntityRepository.save(medicalHistoryEntity);
    }

    @Override
    public void insertToothRelationship(Long medicalRecordId, Long toothId) {
        this.medicalHistoryEntityRepository.insertToothRelationship(medicalRecordId, toothId);
    }

    @Override
    public List<Long> findRelatedTeeth(Long medicalRecordId) {
        return this.medicalHistoryEntityRepository.findRelatedTeeth(medicalRecordId);
    }


    @Override
    public MedicalHistory save(MedicalHistoryEntity medicalHistory) {
        return medicalHistoryPersistanceMapper.toMedicalHistory(this.medicalHistoryEntityRepository.save(medicalHistory));
    }

    @Override
    public List<MedicalRecordEntryEntity> findMedicalRecordsByHistoryId(Long id) {
        return this.medicalHistoryEntityRepository.findMedicalRecordsByHistoryId(id);
    }
}
