package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.AppointmentServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.UserServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.AppointmentPersistancePort;
import com.smile.clinic.smile_clinic.application.ports.output.PatientPersistancePort;
import com.smile.clinic.smile_clinic.application.ports.output.UserPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.AppointmentNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.appointments.Appointment;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.AppointmentFormDTO;
import com.smile.clinic.smile_clinic.utils.DateTimeComposer;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class AppointmentService implements AppointmentServicePort {

    private final AppointmentPersistancePort appointmentPersistancePort;
    private final UserPersistancePort userPersistancePort;
    private final PatientPersistancePort patientPersistancePort;
    private final DateTimeComposer dateTimeComposer;

    @Override
    public List<Appointment> findAll() {
        return this.appointmentPersistancePort.findAll();
    }

    @Override
    public Appointment findById(Long id) throws AppointmentNotFoundException {
        Optional<Appointment> appointment = this.appointmentPersistancePort.findById(id);

        return appointment.orElseThrow();
    }

    @Override
    public List<Appointment> findByClinicId(Long clinicId) {
        return this.appointmentPersistancePort.findByClinicId(clinicId);
    }

    @Override
    public List<Appointment> findByUserId(Long userId) {
        return this.appointmentPersistancePort.findByUserId(userId);
    }

    @Override
    public List<Appointment> findByPatientId(Long patientId) {
        return this.appointmentPersistancePort.findByPatientId(patientId);
    }

    @Override
    public Appointment save(AppointmentFormDTO appointmentFormDTO) {
        LocalDateTime dateTime = this.dateTimeComposer.composeDateTime(appointmentFormDTO.getDate(), appointmentFormDTO.getTime());
        User user = this.userPersistancePort.findById(appointmentFormDTO.getUserId()).orElseThrow();
        Patient patient = this.patientPersistancePort.findById(appointmentFormDTO.getPatientId()).orElseThrow();

        Appointment appointmentToSave = Appointment.builder()
                .dateTime(dateTime)
                .user(user)
                .patient(patient)
                .duration(appointmentFormDTO.getDuration())
                .visitPurpose(appointmentFormDTO.getVisitPurpose())
                .build();

        return this.appointmentPersistancePort.save(appointmentToSave);
    }

    @Override
    public Appointment update(AppointmentFormDTO appointmentFormDTO) throws AppointmentNotFoundException {
        Optional<Appointment> existingAppointment = this.appointmentPersistancePort.findById(appointmentFormDTO.getId());
        if (existingAppointment.isEmpty()) {
            throw new AppointmentNotFoundException("Appointment with id " + appointmentFormDTO.getId() + " not found");
        }
        Appointment appointmentToSave = existingAppointment.get();

        appointmentToSave.setDuration(appointmentFormDTO.getDuration());
        appointmentToSave.setVisitPurpose(appointmentFormDTO.getVisitPurpose());
        // Update dateTime
        LocalDateTime dateTime = this.dateTimeComposer.composeDateTime(appointmentFormDTO.getDate(), appointmentFormDTO.getTime());
        appointmentToSave.setDateTime(dateTime);

        User user = this.userPersistancePort.findById(appointmentFormDTO.getUserId()).orElseThrow();
        Patient patient = this.patientPersistancePort.findById(appointmentFormDTO.getPatientId()).orElseThrow();

        appointmentToSave.setUser(user);
        appointmentToSave.setPatient(patient);

        return this.appointmentPersistancePort.save(appointmentToSave);
    }

    @Override
    public void deleteByPatientId(Long id) {
        this.appointmentPersistancePort.deleteByPatientId(id);
    }

    @Override
    public void delete(Long id) {
        this.appointmentPersistancePort.deleteById(id);
    }

}
