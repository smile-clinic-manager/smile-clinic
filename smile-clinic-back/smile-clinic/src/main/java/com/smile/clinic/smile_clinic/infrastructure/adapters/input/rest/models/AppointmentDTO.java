package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentDTO {
    private Long id;
    private Double duration;
    private String visitPurpose;
    private LocalDateTime dateTime;
}
