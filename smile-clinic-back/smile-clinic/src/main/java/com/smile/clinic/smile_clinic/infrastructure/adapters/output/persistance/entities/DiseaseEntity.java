package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "diseases")
public class DiseaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_seq")
    @SequenceGenerator(name = "patient_seq", sequenceName = "patient_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String name;
}
