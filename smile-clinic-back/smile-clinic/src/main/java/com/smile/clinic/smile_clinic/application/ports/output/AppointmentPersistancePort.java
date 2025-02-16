package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.appointments.Appointment;

import java.util.List;
import java.util.Optional;

public interface AppointmentPersistancePort {
    List<Appointment> findAll();
    Optional<Appointment> findById(Long id);
    Appointment save(Appointment appointment);
    void deleteById(Long id);
}
