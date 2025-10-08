package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.PreviousDiseasesDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PreviousDiseasesMapper {
    PreviousDiseasesDTO toPreviousDiseasesDTO(PreviousDiseases previousDiseases);
    PreviousDiseases toPreviousDiseases(PreviousDiseasesDTO previousDiseasesDTO);

    List<PreviousDiseases> toPreviousDiseasesList(List<PreviousDiseasesDTO> previousDiseasesEntities);
    List<PreviousDiseasesDTO> toPreviousDiseasesDTOList(List<PreviousDiseases> previousDiseases);
}
