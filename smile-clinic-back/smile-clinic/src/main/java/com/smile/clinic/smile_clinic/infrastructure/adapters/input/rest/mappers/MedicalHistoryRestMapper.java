package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = MedicalRecordEntryRestMapper.class)
public interface MedicalHistoryRestMapper {
        MedicalHistoryDTO toMedicalHistoryDTO(MedicalHistory medicalHistory);
        MedicalHistory toMedicalHistory(MedicalHistoryDTO medicalHistoryDTO);

        List<MedicalHistory> toMedicalHistoryList(List<MedicalHistoryDTO> medicalHistoryEntities);
        List<MedicalHistoryDTO> toMedicalHistoryDTOList(List<MedicalHistory> medicalHistories);

}
