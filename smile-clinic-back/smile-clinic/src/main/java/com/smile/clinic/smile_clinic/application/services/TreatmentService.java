package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.TreatmentServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.TreatmentPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Treatment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentService implements TreatmentServicePort {

    private final TreatmentPersistancePort treatmentPersistancePort;

    @Override
    public List<Treatment> findAll() {
        return treatmentPersistancePort.findAll();
    }

    @Override
    public Treatment findByIdentifier(String identifier) {
        return treatmentPersistancePort.findByIdentifier(identifier)
                .orElseThrow(()-> new RuntimeException("Treatment with identifier "+identifier+" not found"));
    }

    @Override
    public List<Treatment> findIfNameContains(String substring) {
        return treatmentPersistancePort.findIfNameContains(substring);
    }

    @Override
    public List<Treatment> findByPriceBetween(Double minPrice, Double maxPrice) {
        return treatmentPersistancePort.findByPriceBetween(minPrice, maxPrice);
    }

    @Override
    public List<Treatment> findIfDescriptionContains(String substring) {
        return treatmentPersistancePort.findIfDescriptionContains(substring);
    }

    @Override
    public Treatment save(Treatment treatment) {
        return treatmentPersistancePort.save(treatment);
    }

    @Override
    public Treatment update(String identifier, Treatment treatment) {
        return treatmentPersistancePort.update(identifier, treatment);
    }

    @Override
    public void delete(Treatment treatment) {
        treatmentPersistancePort.delete(treatment);
    }
}
