package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.AppointmentServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.MedicalHistoryServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.PatientServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.TreatmentInstanceServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.MedicalHistoryPersistancePort;
import com.smile.clinic.smile_clinic.application.ports.output.PatientPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PatientService implements PatientServicePort {
    private final TreatmentInstanceServicePort treatmentInstanceServicePort;
    private final AppointmentServicePort appointmentServicePort;
    private final PatientPersistancePort patientPersistancePort;
    private final MedicalHistoryServicePort medicalHistoryServicePort;

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
        MedicalHistory medicalHistory = this.medicalHistoryServicePort.updateMedicalHistory(patient.getMedicalHistory());
        patient.setMedicalHistory(medicalHistory);

        return updateSavedPatient(id, patient);
    }

    private Patient updateSavedPatient(Long id, Patient patient) {
        return patientPersistancePort.findById(id)
                .map((savedPatient) -> {
                    savedPatient.setFirstName(patient.getFirstName());
                    savedPatient.setLastName1(patient.getLastName1());
                    savedPatient.setLastName2(patient.getLastName2());
                    savedPatient.setDni(patient.getDni());
                    savedPatient.setEmail(patient.getEmail());
                    savedPatient.setPhoneNumber(patient.getPhoneNumber());
                    savedPatient.setMedicalHistory(patient.getMedicalHistory());
                    return patientPersistancePort.save(savedPatient);
                })
                .orElseThrow(() -> new RuntimeException("Patient not found"));
    }

    @Override
    public void delete(Long id) {
        treatmentInstanceServicePort.deleteByPatientId(id);
        appointmentServicePort.deleteByPatientId(id); //Borramos los appointments asociados al paciente
        patientPersistancePort.deleteById(id);
    }
}
