package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalHistoryRestMapper {
        MedicalHistoryDTO toMedicalHistoryDTO(MedicalHistory medicalHistory);
        MedicalHistory toMedicalHistory(MedicalHistoryDTO medicalHistoryDTO);

        List<MedicalHistory> toMedicalHistoryList(List<MedicalHistoryDTO> medicalHistoryEntities);
        List<MedicalHistoryDTO> toMedicalHistoryDTOList(List<MedicalHistory> medicalHistories);

}
