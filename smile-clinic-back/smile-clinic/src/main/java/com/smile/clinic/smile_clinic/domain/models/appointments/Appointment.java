package com.smile.clinic.smile_clinic.domain.models.appointments;

import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Appointment {

    private Long id;
    private double duration;
    private String visitPurpose;
    private LocalDateTime dateTime;

    //Relations
    private User user;
    private Patient patient;

}
