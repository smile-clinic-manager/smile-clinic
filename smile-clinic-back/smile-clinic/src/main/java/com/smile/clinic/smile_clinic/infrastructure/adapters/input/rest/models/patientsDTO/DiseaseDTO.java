package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.patientsDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DiseaseDTO {
    @NotNull(message = "Required parameter 'id'")
    private Long id;

    @NotNull(message = "Required parameter 'name'")
    private String name;
}
