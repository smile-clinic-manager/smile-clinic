package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.MedicalRecordEntryServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.MedicalRecordEntryPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordEntryService implements MedicalRecordEntryServicePort {

    private final MedicalRecordEntryPersistancePort medicalRecordEntryPersistancePort;

    @Override
    public List<MedicalRecordEntry> findAll() {
        return medicalRecordEntryPersistancePort.findAll();
    }

    @Override
    public MedicalRecordEntry findById(Long id) {
        return medicalRecordEntryPersistancePort.findById(id);
    }

    @Override
    public MedicalRecordEntry save(MedicalRecordEntry MedicalRecordEntry) {
        return medicalRecordEntryPersistancePort.save(MedicalRecordEntry);
    }

    @Override
    public MedicalRecordEntry update(Long id, MedicalRecordEntry MedicalRecordEntry) {
        return medicalRecordEntryPersistancePort.update(id, MedicalRecordEntry);
    }

    @Override
    public void delete(MedicalRecordEntry MedicalRecordEntry) {
        medicalRecordEntryPersistancePort.delete(MedicalRecordEntry);
    }

    @Override
    public List<MedicalRecordEntry> findAllByMedicalHistory(Long medicalHistoryId) {
        return medicalRecordEntryPersistancePort.findAllByMedicalHistory(medicalHistoryId);
    }
}
