package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
    /*
    * AQUÍ ES DONDE SE METEN LAS VALIDACIONES DE LAS ENTIDADES
   */

    @NotBlank(message = "Field firstName cannot be empty, blank or null")
    private String firstName;
    @NotBlank(message = "Field lastName cannot be empty, blank or null")
    private String lastName;
    @NotBlank(message = "Field address cannot be empty, blank or null")
    private String address;

}
