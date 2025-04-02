package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.PatientPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
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
        return patientEntityRepository.findById(id).map(mapper::toPatient);
    }

    @Override
    public List<Patient> findAll() {
        return mapper.toPatientList(this.patientEntityRepository.findAll());
    }

    @Override
    public List<Patient> findByClinicId(Long clinicId) {
        return mapper.toPatientList(this.patientEntityRepository.findPatientsByClinicId(clinicId));
    }

    @Override
    public Patient save(Patient patient) {
        return mapper.toPatient(this.patientEntityRepository.save(mapper.toPatientEntity(patient)));
    }

    @Override
    public void deleteById(Long id) {
        this.patientEntityRepository.deleteById(id);
    }
}
