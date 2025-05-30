package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import com.smile.clinic.smile_clinic.domain.models.appointments.AppointmentState;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.domain.models.users.DentistData;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.patientsDTO.PatientDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private double duration;
    private String visitPurpose;
    private LocalDateTime dateTime;
    private DentistData user;
    private PatientDTO patient;
}
