package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.PreviousDiseasesPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.PreviousDiseasesPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.PreviousDiseasesEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class PreviousDiseasesAdapter implements PreviousDiseasesPersistancePort {
    private final PreviousDiseasesEntityRepository previousDiseasesEntityRepository;
    private final PreviousDiseasesPersistanceMapper previousDiseasesPersistanceMapper;

    @Override
    public List<PreviousDiseases> getByMedicalHistoryId(Long medicalHistoryId) {
        return this.previousDiseasesPersistanceMapper.toPreviousDiseasesList(
                this.previousDiseasesEntityRepository.findByMedicalHistoryId(medicalHistoryId));
    }
}
