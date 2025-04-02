package com.smile.clinic.smile_clinic.domain.models.patients;

import com.smile.clinic.smile_clinic.domain.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.Clinic;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;

import java.util.List;

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
    private String allergies; //List<Enum>?
    private List<Disease> previousDiseases;
    private Clinic clinic;
    private MedicalHistory medicalHistory;
}
