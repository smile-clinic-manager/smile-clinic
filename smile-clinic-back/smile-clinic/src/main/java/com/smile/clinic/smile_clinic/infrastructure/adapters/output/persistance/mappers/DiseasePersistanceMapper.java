package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.patients.Disease;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.DiseaseEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface DiseasePersistanceMapper {
    Disease toDisease(DiseaseEntity diseaseEntity);
    DiseaseEntity toDisease(Disease disease);
    List<Disease> toDiseaseList(List<DiseaseEntity> diseaseEntityList);
    List<DiseaseEntity> toDiseaseEntityList(List<Disease> diseaseList);

}
