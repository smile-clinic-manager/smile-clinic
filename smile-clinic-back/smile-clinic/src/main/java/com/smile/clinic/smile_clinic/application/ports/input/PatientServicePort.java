package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.patients.Patient;

import java.util.List;

public interface PatientServicePort {
    List<Patient> findAll();
    Patient findById(Long id);
    List<Patient> findByClinicId(Long clinicId);
    Patient create(Patient patient);
    Patient update(Long id, Patient patient);
    void delete(Long id);
}
