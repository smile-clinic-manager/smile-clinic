package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.authenticationsDTO;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Field 'username' required")
    @Length(min = 5, max = 25, message = "Username must be 5-25 characters long")
    private String username;

    @NotBlank(message = "Field 'password' required")
    private String password;
}
