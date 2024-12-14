package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.PatientServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.PatientPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService implements PatientServicePort {

    private final PatientPersistancePort patientPersistancePort;

    @Override
    public List<Patient> findAll() {
        return null;
    }

    @Override
    public List<Patient> findById(Long id) {
        return null;
    }

    @Override
    public Patient save(Patient patient) {
        return null;
    }

    @Override
    public Patient update(Long id, Patient patient) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
