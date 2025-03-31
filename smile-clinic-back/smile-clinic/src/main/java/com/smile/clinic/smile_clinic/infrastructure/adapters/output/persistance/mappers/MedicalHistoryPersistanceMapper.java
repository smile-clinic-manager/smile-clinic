package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.MedicalHistory;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalHistoryEntity;
import java.util.List;

public interface MedicalHistoryPersistanceMapper {
    MedicalHistoryEntity toMedicalHistoryEntity(MedicalHistory medicalHistory);
    MedicalHistory toMedicalHistory(MedicalHistoryEntity medicalHistoryEntity);

    List<MedicalHistory> toMedicalHistoryList(List<MedicalHistoryEntity> medicalHistoryEntities);
    List<MedicalHistoryEntity> toMedicalHistoryEntityList(List<MedicalHistory> medicalHistories);
}
