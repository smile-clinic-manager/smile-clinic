package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.TreatmentDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = PatientRestMapper.class)
public interface TreatmentRestMapper {

    Treatment toTreatment(TreatmentDTO treatmentDTO);
    TreatmentDTO toTreatmentDTO(Treatment treatment);

    List<Treatment> toTreatmentList(List<TreatmentDTO> treatmentsDTO);
    List<TreatmentDTO> toTreatmentDTOList(List<Treatment> treatments);
}
