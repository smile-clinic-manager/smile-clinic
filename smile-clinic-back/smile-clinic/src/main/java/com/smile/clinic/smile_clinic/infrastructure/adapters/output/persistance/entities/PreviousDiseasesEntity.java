package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "previous_diseases")
public class PreviousDiseasesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patients_seq")
    @SequenceGenerator(name = "patients_seq", sequenceName = "patients_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "previousDiseases")
    private Set<MedicalHistoryEntity> medicalHistories = new HashSet<>();
}

