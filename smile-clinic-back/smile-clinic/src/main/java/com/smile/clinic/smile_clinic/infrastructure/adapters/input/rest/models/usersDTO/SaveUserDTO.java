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
public class SaveUserDTO implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName1;

    private String lastName2;

    @NotBlank
    @Column(unique = true)
    @Length(min = 5, max = 25)
    private String username;

    @NotBlank
    @Column(unique = true)
    @Pattern(regexp = "^\\d{8}[A-Z]$")
    private String dni;

    @Email
    @Column(unique = true)
    private String email;

    @NotBlank
    private String password;

    @NotBlank
    private String repeatPassword;

}
