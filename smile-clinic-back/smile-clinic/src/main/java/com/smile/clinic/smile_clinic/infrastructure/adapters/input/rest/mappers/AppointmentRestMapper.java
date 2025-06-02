package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.appointments.Appointment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.AppointmentDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.AppointmentFormDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.UserToDentistDataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE, uses = UserToDentistDataMapper.class)
public interface AppointmentRestMapper {
    Appointment toAppointment(AppointmentDTO appointmentDTO);
    AppointmentDTO toAppointmentDTO(Appointment appointment);

    AppointmentDTO toAppointmentDTOFromForm(AppointmentFormDTO appointmentFormDTO);

    List<Appointment> toAppointmentList(List<AppointmentDTO> appointmentDTO);
    List<AppointmentDTO> toAppointmentDTOList(List<Appointment> appointments);
}
