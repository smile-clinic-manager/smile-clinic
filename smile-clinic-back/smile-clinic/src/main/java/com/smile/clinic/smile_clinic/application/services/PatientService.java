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
        return patientPersistancePort.findAll();
    }

    @Override
    public Patient findById(Long id) {
        return patientPersistancePort.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Override
    public List<Patient> findByClinicId(Long clinicId) {
        return patientPersistancePort.findByClinicId(clinicId);
    }

    @Override
    public Patient save(Patient patient) {
        return patientPersistancePort.save(patient);
    }

    @Override
    public Patient update(Long id, Patient patient) {
        return patientPersistancePort.findById(id)
                .map((savedPatient) -> {
                    savedPatient.setFirstName(patient.getFirstName());
                    savedPatient.setLastName1(patient.getLastName1());
                    savedPatient.setLastName2(patient.getLastName2());
                    savedPatient.setDni(patient.getDni());
                    savedPatient.setEmail(patient.getEmail());
                    savedPatient.setTelephoneNumber(patient.getTelephoneNumber());
                    return patientPersistancePort.save(savedPatient);
                })
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Override
    public void delete(Long id) {

    }
}
