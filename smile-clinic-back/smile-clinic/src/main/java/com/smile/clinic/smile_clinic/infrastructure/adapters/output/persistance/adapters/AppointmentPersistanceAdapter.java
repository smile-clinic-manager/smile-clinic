package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.AppointmentPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.appointments.Appointment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.AppointmentRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.AppointmentPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.AppointmentEntityRepository;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.ClinicEntityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AppointmentPersistanceAdapter implements AppointmentPersistancePort {
    private final AppointmentEntityRepository appointmentEntityRepository;
    private final AppointmentPersistanceMapper mapper;

    @Override
    public List<Appointment> findAll() {
        return this.mapper.toAppointmentList(this.appointmentEntityRepository.findAll());
    }

    @Override
    public Optional<Appointment> findById(Long id) {
        return this.appointmentEntityRepository.findById(id).map(mapper::toAppointment);
    }

    @Override
    public Appointment save(Appointment appointment) {
        return mapper.toAppointment(this.appointmentEntityRepository.save(mapper.toAppointmentEntity(appointment)));
    }

    @Override
    public void deleteById(Long id) {
        this.appointmentEntityRepository.deleteById(id);
    }
}
