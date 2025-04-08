package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.PreviousDiseasesServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.PreviousDiseasesPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PreviousDiseasesService implements PreviousDiseasesServicePort {
    private final PreviousDiseasesPersistancePort previousDiseasesPersistancePort;
    @Override
    public List<PreviousDiseases> getByMedicalHistoryId(Long medicalHistoryId) {
        return this.previousDiseasesPersistancePort.getByMedicalHistoryId(medicalHistoryId);
    }
}
