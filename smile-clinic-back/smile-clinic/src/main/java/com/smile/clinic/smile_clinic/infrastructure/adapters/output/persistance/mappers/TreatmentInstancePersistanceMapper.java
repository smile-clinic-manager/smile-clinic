package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.TreatmentInstanceEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentInstancePersistanceMapper {
    TreatmentInstanceEntity toTreatmentEntity(TreatmentInstance treatment);
    TreatmentInstance toTreatment(TreatmentInstanceEntity entity);

    List<TreatmentInstance> toTreatmentList(List<TreatmentInstanceEntity> entities);
    List<TreatmentInstanceEntity> toTreatmentEntityList(List<TreatmentInstance> treatments);
}
