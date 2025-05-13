package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.MedicalRecordEntryPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.RecordPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.RecordEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MedicalRecordEntryPersistanceAdapter implements MedicalRecordEntryPersistancePort {

    private final RecordEntityRepository recordEntityRepository;
    private final RecordPersistanceMapper recordPersistanceMapper;

    @Override
    public List<MedicalRecordEntry> findAll() {
        return recordPersistanceMapper.toMedicalRecordEntryList(this.recordEntityRepository.findAll());
    }

    @Override
    public MedicalRecordEntry findById(Long id) {
        return recordPersistanceMapper.toMedicalRecordEntry(this.recordEntityRepository.findById(id).orElse(null));
    }

    @Override
    public MedicalRecordEntry save(MedicalRecordEntry record) {
        return recordPersistanceMapper.toMedicalRecordEntry(this.recordEntityRepository.save(recordPersistanceMapper.toMedicalRecordEntryEntity(record)));
    }

    @Override
    public MedicalRecordEntry update(Long id, MedicalRecordEntry record) {
        return recordPersistanceMapper.toMedicalRecordEntry(this.recordEntityRepository.save(recordPersistanceMapper.toMedicalRecordEntryEntity(record)));
    }

    @Override
    public void delete(MedicalRecordEntry record) {
        this.recordEntityRepository.delete(recordPersistanceMapper.toMedicalRecordEntryEntity(record));
    }

    @Override
    public List<MedicalRecordEntry> findPatientToothMedicalRecords(Long medicalRecordId, Long toothId) {
        //TODO
        return null;
    }
}
