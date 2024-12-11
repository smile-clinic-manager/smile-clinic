package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import jakarta.persistence.ForeignKey;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClinicDTO {

    //owner key
    private int ownerId;

    @NotBlank(message = "Field name cannot be empty, blank or null")
    private String name;

    @NotBlank(message = "Field address cannot be empty, blank or null")
    private String address;

    @NotBlank(message = "Field phone cannot be empty, blank or null")
    private String phone;

    @NotBlank(message = "Field email cannot be empty, blank or null")
    private String email;

    private String website;

    private String description;

    private String image;
}
