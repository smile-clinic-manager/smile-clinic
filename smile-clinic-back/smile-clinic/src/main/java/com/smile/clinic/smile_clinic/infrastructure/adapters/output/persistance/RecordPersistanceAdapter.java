package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance;

import com.smile.clinic.smile_clinic.application.ports.output.RecordPersistancePort;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.RecordPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.RecordEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class RecordPersistanceAdapter implements RecordPersistancePort {

    private final RecordEntityRepository recordEntityRepository;
    private final RecordPersistanceMapper recordPersistanceMapper;

    @Override
    public List<Record> findAll() {
        return recordPersistanceMapper.toRecordList(this.recordEntityRepository.findAll());
    }

    @Override
    public Record findById(Long id) {
        return recordPersistanceMapper.toRecord(this.recordEntityRepository.findById(id).orElse(null));
    }

    @Override
    public List<Record> findByPatientId(int patientId) {
        return recordPersistanceMapper.toRecordList(this.recordEntityRepository.findByPatientId(patientId));
    }

    @Override
    public List<Record> findByUserId(int userId) {
        return recordPersistanceMapper.toRecordList(this.recordEntityRepository.findByUserId(userId));
    }

    @Override
    public List<Record> findByTreatmentIdentifier(String treatmentIdentifier) {
        return recordPersistanceMapper.toRecordList(this.recordEntityRepository.findByTreatmentIdentifier(treatmentIdentifier));
    }

    @Override
    public Record save(Record record) {
        return recordPersistanceMapper.toRecord(this.recordEntityRepository.save(recordPersistanceMapper.toRecordEntity(record)));
    }

    @Override
    public Record update(Long id, Record record) {
        return recordPersistanceMapper.toRecord(this.recordEntityRepository.save(recordPersistanceMapper.toRecordEntity(record)));
    }

    @Override
    public void delete(Record record) {
        this.recordEntityRepository.delete(recordPersistanceMapper.toRecordEntity(record));
    }
}
