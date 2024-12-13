package com.smile.clinic.smile_clinic.domain.models;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Record {
    private int id;
    private int patientId; //Patient patient ?
    private int userId; //id de quien actualiza el record
    private String treatmentIdentifier;
    private LocalDateTime dateTime;
    private String notes;
}
