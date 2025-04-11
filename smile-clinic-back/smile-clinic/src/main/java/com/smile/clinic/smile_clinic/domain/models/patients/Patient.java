package com.smile.clinic.smile_clinic.domain.models.patients;

import com.smile.clinic.smile_clinic.domain.models.Clinic;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Patient {
    private Long id;
    private String firstName;
    private String lastName1;
    private String lastName2;
    private String dni;
    private String email;
    private String telephoneNumber;
    private Clinic clinic;
    //private MedicalHistory medicalHistory;
}
