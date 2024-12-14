package com.smile.clinic.smile_clinic.application.ports.input;

import java.util.List;

public interface RecordServicePort {

    List<Record> findAll();

    Record findById(Long id);
    List<Record> findByPatientId(int patientId);
    List<Record> findByUserId(int userId);
    List<Record> findByTreatmentIdentifier(String treatmentIdentifier);

    Record save(Record record);
    Record update(Long id, Record record);
    void delete(Record record);
}
