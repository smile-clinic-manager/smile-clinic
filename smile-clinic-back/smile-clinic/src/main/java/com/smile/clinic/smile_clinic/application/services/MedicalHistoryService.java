package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.MedicalHistoryServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.MedicalHistoryPersistancePort;
import com.smile.clinic.smile_clinic.application.ports.output.PatientPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;
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

    @Override
    public MedicalHistory getMedicalHistoryByPatientId(Long patientId) {
        return this.medicalHistoryPersistancePort.getMedicalHistoryByPatientId(patientId);
    }

    @Override
    public MedicalHistory updateMedicalHistory(MedicalHistory medicalHistory) {
        return medicalHistoryPersistancePort.findById(medicalHistory.getId()).map((savedMedicalHistory) -> {
                savedMedicalHistory.setAllergies(medicalHistory.getAllergies());
                savedMedicalHistory.setPreviousDiseases(medicalHistory.getPreviousDiseases());
                return medicalHistoryPersistancePort.save(savedMedicalHistory);
            })
            .orElseThrow(() -> new RuntimeException("Medical history not found"));
    }
}
