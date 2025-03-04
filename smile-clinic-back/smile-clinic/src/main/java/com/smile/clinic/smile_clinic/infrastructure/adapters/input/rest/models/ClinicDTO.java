package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import jakarta.persistence.ForeignKey;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDTO {
    @NotBlank(message = "Field id cannot be empty, blank or null")
    private Long id;

    @NotBlank(message = "Field name cannot be empty, blank or null")
    private String name;

    @NotBlank(message = "Field address cannot be empty, blank or null")
    private String address;

    @NotBlank(message = "Field postalCode cannot be empty, blank or null")
    private String postalCode;

    @NotBlank(message = "Field phone cannot be empty, blank or null")
    private String phoneNumber;

    @NotBlank(message = "Field email cannot be empty, blank or null")
    private String email;

    private String image;

    // Relationships
    @NotEmpty(message = "Field treatments cannot be empty")
    private List<TreatmentDTO> treatments;
}
