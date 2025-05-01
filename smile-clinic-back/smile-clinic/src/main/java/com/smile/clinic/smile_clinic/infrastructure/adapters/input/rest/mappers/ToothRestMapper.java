package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.Tooth;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.ToothDTO;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring", uses = MedicalRecordEntryRestMapper.class)
public interface ToothRestMapper {
    Tooth toTooth(ToothDTO treatmentDTO);
    ToothDTO toToothDTO(Tooth treatment);

    List<Tooth> toToothList(List<ToothDTO> treatmentsDTO);
    List<ToothDTO> toToothDTOList(List<Tooth> treatments);
}
