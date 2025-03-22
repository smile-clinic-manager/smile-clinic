package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.validator.constraints.time.DurationMin;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TreatmentDTO {
    private Long id;

    @NotBlank(message = "Field name cannot be empty, blank or null")
    private String name;

    @NotNull(message = "Field price cannot be null")
    @Min(value = 0, message = "Field price cannot be negative")
    private Double price;

    @NotBlank(message = "Field notes cannot be empty, blank or null")
    private String notes;
}
