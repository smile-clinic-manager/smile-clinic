package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance;

import com.smile.clinic.smile_clinic.application.ports.output.TreatmentPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.ClinicNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.TreatmentPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.TreatmentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TreatmentPersistanceAdapter implements TreatmentPersistancePort {

    private final TreatmentEntityRepository treatmentEntityRepository;
    private final TreatmentPersistanceMapper treatmentPersistanceMapper;

    @Override
    public List<Treatment> findAll() {
        return treatmentPersistanceMapper.toTreatmentList(this.treatmentEntityRepository.findAll());
    }

    @Override
    public Optional<Treatment> findByIdentifier(String identifier) {
        return this.treatmentEntityRepository.findByIdentifier(identifier);
    }

    @Override
    public List<Treatment> findIfNameContains(String substring) {
        return treatmentPersistanceMapper.toTreatmentList(this.treatmentEntityRepository.findByNameContaining(substring));
    }

    @Override
    public List<Treatment> findByPriceBetween(double minPrice, double maxPrice) {
        return treatmentPersistanceMapper.toTreatmentList(this.treatmentEntityRepository.findByPriceBetween(minPrice, maxPrice));
    }

    @Override
    public List<Treatment> findIfDescriptionContains(String substring) {
        return treatmentPersistanceMapper.toTreatmentList(this.treatmentEntityRepository.findIfDescriptionContains(substring));
    }

    @Override
    public Treatment save(Treatment treatment) {
        return treatmentPersistanceMapper.toTreatment(this.treatmentEntityRepository.save(treatmentPersistanceMapper.toTreatmentEntity(treatment)));
    }

    @Override
    public Treatment update(String identifier, Treatment treatment) {
        return treatmentPersistanceMapper.toTreatment(this.treatmentEntityRepository.save(treatmentPersistanceMapper.toTreatmentEntity(treatment)));
    }

    @Override
    public void delete(Treatment treatment) {
        this.treatmentEntityRepository.delete(treatmentPersistanceMapper.toTreatmentEntity(treatment));
    }
}
