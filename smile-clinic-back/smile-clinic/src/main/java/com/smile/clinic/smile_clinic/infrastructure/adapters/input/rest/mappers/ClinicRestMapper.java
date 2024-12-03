package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.ClinicDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ClinicRestMapper {

    Clinic toClinic(ClinicDTO clinicDTO);
    ClinicDTO toClinicDTO(Clinic clinic);

    List<Clinic> toClinicList(List<ClinicDTO> clinicsDTO);
    List<ClinicDTO> toClinicDTOList(List<Clinic> clinics);
}
