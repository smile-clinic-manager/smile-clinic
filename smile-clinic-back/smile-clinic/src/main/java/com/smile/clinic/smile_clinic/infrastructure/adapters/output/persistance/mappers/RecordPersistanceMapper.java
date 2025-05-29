package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = {TreatmentInstancePersistanceMapper.class, ToothPersistanceMapper.class})
public interface RecordPersistanceMapper {

    MedicalRecordEntryEntity toMedicalRecordEntryEntity(MedicalRecordEntry medicalRecordEntry);
    MedicalRecordEntry toMedicalRecordEntry(MedicalRecordEntryEntity medicalRecordEntryEntity);
    List<MedicalRecordEntryEntity> toRecordEntityList(List<MedicalRecordEntry> medicalRecordEntries);
    List<MedicalRecordEntry> toMedicalRecordEntryList(List<MedicalRecordEntryEntity> medicalRecordEntryEntities);
}
