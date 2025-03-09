package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ClinicEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClinicPersistanceMapper {

    ClinicEntity toClinicEntity(Clinic clinic);
    Clinic toClinic(ClinicEntity entity);

    List<Clinic> toClinicListFromEntities(List<ClinicEntity> entities);

    List<ClinicEntity> toClinicEntityList(List<Clinic> clinics);
}
