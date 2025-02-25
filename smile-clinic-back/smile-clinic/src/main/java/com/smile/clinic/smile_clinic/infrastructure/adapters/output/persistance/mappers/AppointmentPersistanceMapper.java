package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.appointments.Appointment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.AppointmentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AppointmentPersistanceMapper {
    AppointmentEntity toAppointmentEntity(Appointment clinic);
    Appointment toAppointment(AppointmentEntity entity);

    List<Appointment> toAppointmentList(List<AppointmentEntity> entities);
    List<AppointmentEntity> toAppointmentEntityList(List<Appointment> clinics);
}
