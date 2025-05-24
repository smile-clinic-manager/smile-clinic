package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "treatments_instance")
public class TreatmentInstanceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_instance_seq")
    @SequenceGenerator(name = "treatment_instance_seq", sequenceName = "treatment_instance_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String name;

    @Min(value = 1)
    private Double price;

    @NotBlank
    private String notes;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = true) // Foreign Key
    private PatientEntity patient;
}
