package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.TreatmentPersistancePort;
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
    public Optional<Treatment> findById(Long id) {
        return this.treatmentEntityRepository.findById(id).map(treatmentPersistanceMapper::toTreatment);
    }

    @Override
    public List<Treatment> findAll() {
        return treatmentPersistanceMapper.toTreatmentList(this.treatmentEntityRepository.findAll());
    }

    @Override
    public Treatment save(Treatment treatment) {
        return treatmentPersistanceMapper.toTreatment(this.treatmentEntityRepository.save(treatmentPersistanceMapper.toTreatmentEntity(treatment)));
    }

    @Override
    public Treatment update(Long id, Treatment treatment) {
        return treatmentPersistanceMapper.toTreatment(this.treatmentEntityRepository.save(treatmentPersistanceMapper.toTreatmentEntity(treatment)));
    }

    @Override
    public void delete(Treatment treatment) {
        this.treatmentEntityRepository.delete(treatmentPersistanceMapper.toTreatmentEntity(treatment));
    }

    @Override
    public List<Treatment> findByClinicId(Long clinicId) {
        return treatmentPersistanceMapper.toTreatmentList(this.treatmentEntityRepository.findByClinicId(clinicId));
    }
}
