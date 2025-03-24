package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.patientsDTO;

import com.smile.clinic.smile_clinic.domain.models.patients.Disease;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PatientDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Required parameter 'id'")
    private Long id;

    @NotBlank(message = "Required parameter 'firstName'")
    private String firstName;

    @NotBlank(message = "Required parameter 'lastName1'")
    private String lastName1;

    private String lastName2;

    @NotBlank(message = "Required parameter 'dni'")
    @Pattern(regexp = "^\\d{8}[A-Z]$", message = "DNI must have 8 digits and a capital letter with no spaces. ex: 12345678A")
    private String dni;

    @Email
    @NotBlank(message = "Required parameter 'email'")
    private String email;

    @NotBlank(message = "Required parameter 'phoneNumber'")
    private String phoneNumber;

    @NotNull(message = "Required parameter 'allergies'")
    private String allergies; //Why aren't we making an enum or something?

    @NotBlank(message = "Required parameter 'previousDiseases'")
    private List<Disease> previousDiseases;
}
