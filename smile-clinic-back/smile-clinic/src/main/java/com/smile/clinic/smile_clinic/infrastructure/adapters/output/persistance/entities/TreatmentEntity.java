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
    @Pattern(regexp = "^[a-zA-Z0-9]{6,}$", message = "Field identifier must have at least 6 characters and only letters and numbers")
    private String identifier;

    @NotBlank
    private String name;

    @Min(0)
    private Double price;

    private int duration;

    @NotBlank
    private String notes;
}
