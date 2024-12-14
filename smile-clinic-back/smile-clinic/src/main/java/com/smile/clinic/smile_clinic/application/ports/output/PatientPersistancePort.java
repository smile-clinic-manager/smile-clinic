package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.domain.models.users.User;

import java.util.List;
import java.util.Optional;

public interface PatientPersistancePort {
    Optional<Patient> findById(Long id);
    List<Patient> findAll();
    User save(Patient patient);
    void deleteById(Long id);
}
