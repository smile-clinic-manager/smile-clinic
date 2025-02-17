package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.patientsDTO.PatientDTO;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientRestMapper {
    PatientDTO toPatientDTO(Patient patient);
    Patient toPatient(PatientDTO patientDTO);
    List<PatientDTO> toPatientDTOList(List<Patient> patientList);
    List<Patient> toPatientList(List<PatientDTO> patientDTOList);
}
