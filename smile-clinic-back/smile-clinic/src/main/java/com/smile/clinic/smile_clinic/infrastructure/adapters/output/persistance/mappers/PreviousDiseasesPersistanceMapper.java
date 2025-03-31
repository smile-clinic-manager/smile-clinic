package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.PreviousDiseases;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PreviousDiseasesEntity;

import java.util.List;

public interface PreviousDiseasesPersistanceMapper {
    PreviousDiseasesEntity toPreviousDiseasesEntity(PreviousDiseases previousDiseases);
    PreviousDiseases toPreviousDiseases(PreviousDiseasesEntity previousDiseasesEntity);

    List<PreviousDiseases> toPreviousDiseasesList(List<PreviousDiseasesEntity> previousDiseasesEntities);
    List<PreviousDiseasesEntity> toPreviousDiseasesEntityList(List<PreviousDiseases> previousDiseases);
}
