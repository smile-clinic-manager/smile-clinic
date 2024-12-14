package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.patientsDTO.DiseaseDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.DiseaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiseaseRestMapper {
    DiseaseDTO toDiseaseDTO(DiseaseEntity diseaseEntity);
    DiseaseEntity toDisease(DiseaseDTO diseaseDTO);
    List<DiseaseDTO> toDiseaseDTOList(List<DiseaseEntity> diseaseEntityList);
    List<DiseaseEntity> toDiseaseEntityList(List<DiseaseDTO> diseaseDTOList);
}
