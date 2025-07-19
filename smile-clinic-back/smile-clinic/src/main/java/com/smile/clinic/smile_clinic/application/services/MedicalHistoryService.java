package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.MedicalHistoryServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.MedicalHistoryPersistancePort;
import com.smile.clinic.smile_clinic.application.ports.output.PatientPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.PreviousDiseasesMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalHistoryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.MedicalHistoryPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.PreviousDiseasesPersistanceMapper;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class MedicalHistoryService implements MedicalHistoryServicePort {
    private final MedicalHistoryPersistancePort medicalHistoryPersistancePort;
    private final PreviousDiseasesPersistanceMapper previousDiseasesMapper;
    private final MedicalHistoryPersistanceMapper medicalHistoryPersistanceMapper;

    @Override
    public MedicalHistory getMedicalHistoryByPatientId(Long patientId) {
        return this.medicalHistoryPersistancePort.getMedicalHistoryByPatientId(patientId);
    }

    @Override
    public MedicalHistory updateMedicalHistory(MedicalHistory medicalHistory) {
        return medicalHistoryPersistancePort.findById(medicalHistory.getId())
                .map((savedMedicalHistory) -> {
                    savedMedicalHistory.setAllergies(medicalHistory.getAllergies());
                    savedMedicalHistory.setPreviousDiseases(previousDiseasesMapper.toPreviousDiseasesEntityList(medicalHistory.getPreviousDiseases()));

                    return  medicalHistoryPersistancePort.save(savedMedicalHistory);
                })
            .orElseThrow(() -> new RuntimeException("Medical history not found"));
    }

    @Override
    public void bindRecordToMedicalHistory(Long medicalHistoryId, MedicalRecordEntry record) {
        medicalHistoryPersistancePort.bindRecordToMedicalHistory(medicalHistoryId, record);
    }

    @Override
    public void insertToothRelationship(Long medicalRecordId, Long toothId) {
        medicalHistoryPersistancePort.insertToothRelationship(medicalRecordId, toothId);
    }

    @Override
    public List<Long> findRelatedTeethIds(Long medicalRecordId) {
        return medicalHistoryPersistancePort.findRelatedTeeth(medicalRecordId);
    }
}
