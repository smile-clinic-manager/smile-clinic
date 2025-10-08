package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.PatientPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalHistoryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PatientEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.MedicalHistoryPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.PatientPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.MedicalHistoryEntityRepository;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.PatientEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class PatientPersistanceAdapter implements PatientPersistancePort {

    private final PatientEntityRepository patientEntityRepository;
    private final MedicalHistoryEntityRepository medicalHistoryEntityRepository;
    private final PatientPersistanceMapper mapper;
    private final MedicalHistoryPersistanceMapper historyMapper;


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

    @Override
    public Patient create(Patient patient) {
        PatientEntity patientEntity = mapper.toPatientEntity(patient);
        MedicalHistoryEntity medicalHistory = this.medicalHistoryEntityRepository.save(historyMapper.toMedicalHistoryEntity(patient.getMedicalHistory()));
        patientEntity.setMedicalHistory(medicalHistory);
        return mapper.toPatient(this.patientEntityRepository.save(patientEntity));
    }


}
