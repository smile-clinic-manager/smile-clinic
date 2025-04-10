package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.TreatmentInstanceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentInstanceRestMapper {
    TreatmentInstanceDTO toTreatmentInstanceDTO(TreatmentInstance treatment);
    TreatmentInstance toTreatment(TreatmentInstanceDTO treatmentInstanceDTO);

    List<TreatmentInstance> toTreatmentList(List<TreatmentInstanceDTO> treatmentInstanceDTOS);
    List<TreatmentInstanceDTO> toTreatmentDTOList(List<TreatmentInstance> treatments);
}
