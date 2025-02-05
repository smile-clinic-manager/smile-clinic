package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SignupRequestDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank(message = "Required parameter 'firstName'")
    private String firstName;

    @NotBlank(message = "Required parameter 'lastName1'")
    private String lastName1;

    private String lastName2;

    @NotBlank(message = "Required parameter 'username'")
    @Length(min = 5, max = 25, message="Username must be between 5-25 characters long")
    private String username;

    @NotBlank(message = "Required parameter 'dni'")
    @Pattern(regexp = "^\\d{8}[A-Z]$", message = "DNI must have 8 digits and a capital letter with no spaces. ex: 12345678A")
    private String dni;

    @Email
    @NotBlank(message = "Required parameter 'email'")
    private String email;

    @Email
    @NotBlank(message = "Required parameter 'repeatEmail'")
    private String confirmEmail;

    @NotBlank(message = "Required parameter 'password'")
    private String password;

    @NotBlank(message = "Required parameter 'confirmPassword'")
    private String confirmPassword;

}
