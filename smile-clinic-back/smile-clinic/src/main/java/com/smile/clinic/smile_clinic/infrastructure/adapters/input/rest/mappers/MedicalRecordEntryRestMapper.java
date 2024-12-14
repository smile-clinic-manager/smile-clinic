package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalRecordEntryDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MedicalRecordEntryRestMapper {

    MedicalRecordEntry toMedicalRecordEntry(MedicalRecordEntryDTO recordDTO);
    MedicalRecordEntryDTO toMedicalRecordEntryDTO(MedicalRecordEntry record);

    List<MedicalRecordEntry> toMedicalRecordEntryEntityList(List<MedicalRecordEntryDTO> recordsDTO);
    List<MedicalRecordEntryDTO> toMedicalRecordEntryDTOList(List<MedicalRecordEntry> records);
}
