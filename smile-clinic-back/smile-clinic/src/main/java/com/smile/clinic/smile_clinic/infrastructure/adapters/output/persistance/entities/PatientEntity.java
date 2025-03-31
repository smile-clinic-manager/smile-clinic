package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import com.smile.clinic.smile_clinic.domain.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.patients.Disease;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name="patients")
public class PatientEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_seq")
    @SequenceGenerator(name="patients_seq", sequenceName = "patients_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName1;

    private String lastName2;

    @NotBlank
    @Column(unique = true)
    @Pattern(regexp = "^\\d{8}[A-Z]$")
    private String dni;

    @Email
    @Column(unique = true)
    private String email;

    private String phoneNumber;

    private String allergies;

    //Relationships

    @ManyToMany
    @JoinTable(
            name = "patient_disease", // Join table name
            joinColumns = @JoinColumn(name = "patient_id"), // Foreign key for Student
            inverseJoinColumns = @JoinColumn(name = "disease_id") // Foreign key for Course
    )
    private List<DiseaseEntity> previousDiseases;

    @ManyToOne
    @JoinColumn(name = "clinic_id", nullable = false)
    private ClinicEntity clinic;

    @OneToOne
    @JoinColumn(name = "medical_history_id")
    private MedicalHistoryEntity medicalHistory;

}
