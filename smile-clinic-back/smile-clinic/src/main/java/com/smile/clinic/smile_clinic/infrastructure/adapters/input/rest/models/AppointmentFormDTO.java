package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentFormDTO {
    Long id;
    Integer duration;
    String visitPurpose;
    String date;
    String time;
    Long userId;
    Long patientId;
}
