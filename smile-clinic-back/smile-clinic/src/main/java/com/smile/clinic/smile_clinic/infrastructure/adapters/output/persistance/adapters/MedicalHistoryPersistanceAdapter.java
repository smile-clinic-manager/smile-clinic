package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.MedicalHistoryPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.MedicalHistoryPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.PatientPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.MedicalHistoryEntityRepository;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.PatientEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class MedicalHistoryPersistanceAdapter implements MedicalHistoryPersistancePort {

    private final MedicalHistoryEntityRepository medicalHistoryEntityRepository;
    private final MedicalHistoryPersistanceMapper medicalHistoryPersistanceMapper;

    @Override
    public MedicalHistory getMedicalHistoryByPatientId(Long patientId) {
        MedicalHistory medicalHistories = this.medicalHistoryPersistanceMapper.toMedicalHistory(
                this.medicalHistoryEntityRepository.findByPatientId(patientId)
        );
        return medicalHistories;
    }
}
