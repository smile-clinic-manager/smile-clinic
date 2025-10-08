package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TreatmentInstancePersistanceMapper.class, UserToDentistDataMapper.class})
public interface MedicalRecordEntryPersistanceMapper {
    MedicalRecordEntry toMedicalRecordEntry(MedicalRecordEntryEntity medicalRecordEntryEntity);
    MedicalRecordEntryEntity toMedicalRecordEntryEntity(MedicalRecordEntry medicalRecordEntry);

    List<MedicalRecordEntry> toMedicalRecordEntryList(List<MedicalRecordEntryEntity> medicalRecordEntriesEntity);
    List<MedicalRecordEntryEntity> toMedicalRecordEntryEntityList(List<MedicalRecordEntry> medicalRecordEntries);
}
