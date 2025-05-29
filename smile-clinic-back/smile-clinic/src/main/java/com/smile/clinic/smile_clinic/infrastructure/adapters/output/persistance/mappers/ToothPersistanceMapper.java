package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.Tooth;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.MedicalRecordEntryRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ToothEntity;
import org.mapstruct.Mapper;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring", uses = MedicalRecordEntryPersistanceMapper.class, nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface ToothPersistanceMapper {
    Tooth toTooth(ToothEntity toothEntity);
    ToothEntity toToothEntity(Tooth tooth);
    List<Tooth> toToothList(List<ToothEntity> toothEntity);
    List<ToothEntity> toToothEntityList(List<Tooth> teeth);
}
