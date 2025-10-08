package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.TreatmentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentPersistanceMapper {

    TreatmentEntity toTreatmentEntity(Treatment treatment);
    Treatment toTreatment(TreatmentEntity entity);

    List<Treatment> toTreatmentList(List<TreatmentEntity> entities);
    List<TreatmentEntity> toTreatmentEntityList(List<Treatment> treatments);
}
