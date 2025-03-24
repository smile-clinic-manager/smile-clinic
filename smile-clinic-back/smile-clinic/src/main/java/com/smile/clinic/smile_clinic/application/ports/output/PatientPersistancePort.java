package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.patients.Patient;

import java.util.List;
import java.util.Optional;

public interface PatientPersistancePort {
    Optional<Patient> findById(Long id);
    List<Patient> findAll();
    List<Patient> findByClinicId(Long clinicId);
    Patient save(Patient patient);
    void deleteById(Long id);
}
