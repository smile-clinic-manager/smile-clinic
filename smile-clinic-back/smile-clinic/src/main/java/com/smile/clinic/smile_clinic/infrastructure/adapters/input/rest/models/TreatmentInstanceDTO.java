package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentInstanceDTO {
    private Long id;

    @NotBlank(message = "Field name cannot be empty, blank or null")
    private String name;

    @NotNull(message = "Field price cannot be null")
    @Min(value = 0, message = "Field price cannot be negative")
    private Double price;

    @NotBlank(message = "Field notes cannot be empty, blank or null")
    private String notes;

    private Long patientId;
}
