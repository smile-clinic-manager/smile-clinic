package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.TreatmentInstanceDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TreatmentInstanceRestMapper {
    TreatmentInstanceDTO toTreatmentInstanceDTO(TreatmentInstance treatment);
    TreatmentInstance toTreatmentInstance(TreatmentInstanceDTO treatmentInstanceDTO);

    List<TreatmentInstance> toTreatmentInstanceList(List<TreatmentInstanceDTO> treatmentInstanceDTOS);
    List<TreatmentInstanceDTO> toTreatmentInstanceDTOList(List<TreatmentInstance> treatments);
}
