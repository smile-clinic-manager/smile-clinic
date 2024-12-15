package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientPersistanceMapper {
    Patient toPatient(PatientEntity patientEntity);
    PatientEntity toDisease(Patient patient);
    List<Patient> toDiseaseList(List<PatientEntity> patientEntityList);
    List<PatientEntity> toDiseaseEntityList(List<Patient> patientList);
}