package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "treatments")
public class TreatmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "treatment_seq")
    @SequenceGenerator(name = "treatment_seq", sequenceName = "treatment_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String name;

    @Min(value = 1)
    private Double price;

    @NotBlank
    private String notes;

    @ManyToOne
    @JoinColumn(name = "clinic_id", nullable = false) // Foreign Key
    private ClinicEntity clinic;
}
