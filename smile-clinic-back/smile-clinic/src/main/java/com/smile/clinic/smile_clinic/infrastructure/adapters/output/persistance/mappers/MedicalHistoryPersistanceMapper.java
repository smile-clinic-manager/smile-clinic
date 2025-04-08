package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.PreviousDiseases;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalHistoryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.PreviousDiseasesEntity;
import org.mapstruct.Mapper;

import java.util.List;
import java.util.Set;

@Mapper(componentModel = "spring", uses = PreviousDiseasesPersistanceMapper.class)
public interface MedicalHistoryPersistanceMapper {
    MedicalHistoryEntity toMedicalHistoryEntity(MedicalHistory medicalHistory);
    MedicalHistory toMedicalHistory(MedicalHistoryEntity medicalHistoryEntity);

    List<MedicalHistory> toMedicalHistoryList(List<MedicalHistoryEntity> medicalHistoryEntities);
    List<MedicalHistoryEntity> toMedicalHistoryEntityList(List<MedicalHistory> medicalHistories);

}
