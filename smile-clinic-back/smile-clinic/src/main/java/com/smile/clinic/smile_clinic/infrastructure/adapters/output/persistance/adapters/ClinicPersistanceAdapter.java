package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.ClinicPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.ClinicPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.ClinicEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ClinicPersistanceAdapter implements ClinicPersistancePort {

    private final ClinicEntityRepository clinicRepository;
    private final ClinicPersistanceMapper clinicPersistanceMapper;

    @Override
    public List<Clinic> findAll() {
        return clinicPersistanceMapper.toClinicListFromEntities(this.clinicRepository.findAll());
    }

    @Override
    public List<Clinic> findAllByUserId(Long id) {
        return clinicPersistanceMapper.toClinicListFromEntities(this.clinicRepository.findAllByUserId(id));
    }

    @Override
    public Optional<Clinic> findById(Long id) {
        return this.clinicRepository.findById(id)
                .map(clinicPersistanceMapper::toClinic);
    }

    @Override
    public Optional<Clinic> findByAddress(String address) {
        return this.clinicRepository.findByAddress(address);
    }

    @Override
    public Clinic save(Clinic clinic) {
        return clinicPersistanceMapper.toClinic(this.clinicRepository.save(clinicPersistanceMapper.toClinicEntity(clinic)));
    }

    @Override
    public void deleteById(Long id) {
        this.clinicRepository.deleteById(id);
    }
}
