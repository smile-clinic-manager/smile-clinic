package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.AppointmentServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.AppointmentPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.AppointmentNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.appointments.Appointment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AppointService implements AppointmentServicePort {
    private AppointmentPersistancePort appointmentPersistancePort;

    @Override
    public List<Appointment> findAll() {
        return null;
    }

    @Override
    public Appointment findById(Long id) throws AppointmentNotFoundException {
        Optional<Appointment> appointment = this.appointmentPersistancePort.findById(id);

        return appointment.orElseThrow();
    }

    @Override
    public Appointment save(Appointment appointment) {
        return null;
    }

    @Override
    public Appointment update(Appointment appointment) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }

}
