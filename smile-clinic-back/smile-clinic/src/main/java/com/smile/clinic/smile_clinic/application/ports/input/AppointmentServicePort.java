package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.application.ports.output.AppointmentPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.AppointmentNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.appointments.Appointment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.AppointmentFormDTO;

import java.util.List;

public interface AppointmentServicePort {
    List<Appointment> findAll();
    Appointment findById(Long id) throws AppointmentNotFoundException;
    List<Appointment> findByClinicId(Long clinicId);
    List<Appointment> findByUserId(Long userId);
    List<Appointment> findByPatientId(Long patientId);
    Appointment save(AppointmentFormDTO appointmentFormDTO);
    void delete(Long id);

    Appointment update(AppointmentFormDTO appointmentFormDTO) throws AppointmentNotFoundException;
}
