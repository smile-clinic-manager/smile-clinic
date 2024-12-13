package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecordDTO {

    @NotBlank(message = "Field patientId cannot be empty, blank or null")
    private int patientId;

    @NotBlank(message = "Field userId cannot be empty, blank or null")
    private int userId;

    @NotNull(message = "Field treatmentIdentifier cannot be null")
    private String treatmentIdentifier;

    @NotBlank(message = "Field dateTime cannot be empty, blank or null")
    @Pattern(regexp = "^[0-9]{4}-[0-9]{2}-[0-9]{2} [0-9]{2}:[0-9]{2}$", message = "Field dateTime must have the format yyyy-MM-dd HH:mm")
    private String dateTime;

    @NotNull(message = "Field notes cannot be null")
    private String notes;

}
