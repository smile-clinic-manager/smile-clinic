package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

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

    @NotBlank
    private String phoneNumber;

    //Relationships
    @ManyToOne
    @JoinColumn(name = "clinic_id", nullable = false)
    private ClinicEntity clinic;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "medical_history_id")
    private MedicalHistoryEntity medicalHistory;

}
