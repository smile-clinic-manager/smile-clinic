package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.authenticationsDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDTO implements Serializable {
    private static final long SerialUID = 1L;

    @NotBlank
    private String token;

    @NotBlank
    private String refreshToken;
}
