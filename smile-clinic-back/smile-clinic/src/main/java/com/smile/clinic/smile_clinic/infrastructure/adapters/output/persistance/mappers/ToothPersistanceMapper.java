package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.Tooth;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.MedicalRecordEntryRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ToothEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MedicalRecordEntryPersistanceMapper.class)
public interface ToothPersistanceMapper {
    Tooth toTooth(ToothEntity treatmentEntity);
    ToothEntity toToothEntity(Tooth treatment);

    List<Tooth> toToothList(List<ToothEntity> treatmentsEntity);
    List<ToothEntity> toToothEntityList(List<Tooth> treatments);
}
