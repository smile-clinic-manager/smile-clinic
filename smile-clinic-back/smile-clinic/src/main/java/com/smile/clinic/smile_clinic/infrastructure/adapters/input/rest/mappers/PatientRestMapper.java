package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.patientsDTO.DiseaseDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.patientsDTO.PatientDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.DiseaseEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PatientEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PatientRestMapper {
    PatientDTO toPatientDTO(PatientEntity patientEntity);
    PatientEntity toDisease(PatientDTO patientDTO);
    List<PatientDTO> toDiseaseDTOList(List<PatientEntity> patientEntityList);
    List<PatientEntity> toDiseaseEntityList(List<PatientDTO> patientDTOList);
}
