package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalHistoryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {PreviousDiseasesPersistanceMapper.class, MedicalRecordEntryPersistanceMapper.class})
public interface MedicalHistoryPersistanceMapper {
    MedicalHistoryEntity toMedicalHistoryEntity(MedicalHistory medicalHistory);
    MedicalHistory toMedicalHistory(MedicalHistoryEntity medicalHistoryEntity);

    List<MedicalHistory> toMedicalHistoryList(List<MedicalHistoryEntity> medicalHistoryEntities);
    List<MedicalHistoryEntity> toMedicalHistoryEntityList(List<MedicalHistory> medicalHistories);

}
