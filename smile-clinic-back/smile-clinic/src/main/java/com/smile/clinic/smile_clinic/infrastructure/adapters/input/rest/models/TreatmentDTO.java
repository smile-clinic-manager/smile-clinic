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

    @Pattern(regexp = "^[a-zA-Z0-9]{6,}$", message = "Field identifier must have at least 6 characters and only letters and numbers")
    private String identifier;

    @NotBlank(message = "Field name cannot be empty, blank or null")
    private String name;

    @NotNull(message = "Field price cannot be null")
    @Min(value = 0, message = "Field price cannot be negative")
    private Double price;

    @NotNull(message = "Field duration cannot be null")
    @DurationMin(days = 1, message = "Field duration must be at least 1 day")
    private String duration;

    @NotBlank(message = "Field notes cannot be empty, blank or null")
    private String notes;
}
