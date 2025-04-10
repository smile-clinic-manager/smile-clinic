package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PreviousDiseasesEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring")
public interface PreviousDiseasesPersistanceMapper {
    PreviousDiseasesEntity toPreviousDiseasesEntity(PreviousDiseases previousDiseases);
    PreviousDiseases toPreviousDiseases(PreviousDiseasesEntity previousDiseasesEntity);

    List<PreviousDiseases> toPreviousDiseasesList(List<PreviousDiseasesEntity> previousDiseasesEntities);
    List<PreviousDiseasesEntity> toPreviousDiseasesEntityList(List<PreviousDiseases> previousDiseases);

    Set<PreviousDiseases> toPreviousDiseasesList(Set<PreviousDiseasesEntity> previousDiseasesEntities);
    Set<PreviousDiseasesEntity> toPreviousDiseasesEntityList(Set<PreviousDiseases> previousDiseases);
}
