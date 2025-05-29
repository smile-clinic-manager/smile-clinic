package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.TreatmentInstanceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = PatientPersistanceMapper.class)
public interface TreatmentInstancePersistanceMapper {
    TreatmentInstanceEntity toTreatmentInstanceEntity(TreatmentInstance treatment);
    TreatmentInstance toTreatmentInstance(TreatmentInstanceEntity entity);

    List<TreatmentInstance> toTreatmentInstanceList(List<TreatmentInstanceEntity> entities);
    List<TreatmentInstanceEntity> toTreatmentInstanceEntityList(List<TreatmentInstance> treatments);
}
