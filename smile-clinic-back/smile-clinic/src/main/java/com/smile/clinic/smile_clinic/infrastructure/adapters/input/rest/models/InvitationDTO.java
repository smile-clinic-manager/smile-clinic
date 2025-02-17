package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InvitationDTO {

    @NotBlank(message = "Field status cannot be empty, blank or null")
    private String status;

    @Valid
    private ClinicDTO clinic;

    @Valid
    private RegisteredUserDTO user;

}
