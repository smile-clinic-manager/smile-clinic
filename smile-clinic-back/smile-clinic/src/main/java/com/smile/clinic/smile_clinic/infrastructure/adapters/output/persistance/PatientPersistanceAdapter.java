package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance;

import com.smile.clinic.smile_clinic.application.ports.output.PatientPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.DiseasePersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.PatientPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.PatientEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component //needed bean for initialization of the persistance interface
@RequiredArgsConstructor // alternative to @Entity annotation: much clearer
public class PatientPersistanceAdapter implements PatientPersistancePort {
    private final PatientEntityRepository patientEntityRepository;
    private final PatientPersistanceMapper mapper;

    @Override
    public Optional<Patient> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<Patient> findAll() {
        return null;
    }

    @Override
    public User save(Patient patient) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
