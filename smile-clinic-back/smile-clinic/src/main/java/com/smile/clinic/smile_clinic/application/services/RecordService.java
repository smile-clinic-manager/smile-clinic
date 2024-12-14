package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.RecordServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.RecordPersistancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecordService implements RecordServicePort {

    RecordPersistancePort recordPersistancePort;

    @Override
    public List<Record> findAll() {
        return recordPersistancePort.findAll();
    }

    @Override
    public Record findById(Long id) {
        return recordPersistancePort.findById(id);
    }

    @Override
    public List<Record> findByPatientId(int patientId) {
        return recordPersistancePort.findByPatientId(patientId);
    }

    @Override
    public List<Record> findByUserId(int userId) {
        return recordPersistancePort.findByUserId(userId);
    }

    @Override
    public List<Record> findByTreatmentIdentifier(String treatmentIdentifier) {
        return recordPersistancePort.findByTreatmentIdentifier(treatmentIdentifier);
    }

    @Override
    public Record save(Record record) {
        return recordPersistancePort.save(record);
    }

    @Override
    public Record update(Long id, Record record) {
        return recordPersistancePort.update(id, record);
    }

    @Override
    public void delete(Record record) {
        recordPersistancePort.delete(record);
    }
}
