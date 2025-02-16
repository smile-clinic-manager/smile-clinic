package com.smile.clinic.smile_clinic.domain.models.appointments;

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
    private Double duration;
    private String visitPurpose;
    private LocalDateTime dateTime;

    //Relations
    private User user;

    //TODO: Add patient relationship when implemented! :D

}
