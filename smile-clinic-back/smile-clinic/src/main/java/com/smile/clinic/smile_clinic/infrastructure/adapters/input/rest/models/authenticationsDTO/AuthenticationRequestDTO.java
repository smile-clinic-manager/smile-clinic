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

    @NotBlank(message = "Campo 'nombre de usuario' es obligatorio")
    @Length(min = 5, max = 25, message = "El nombre de usuario debe contener entre 5-25 caracteres")
    private String username;

    @NotBlank(message = "Campo 'contraseña' es obligatorio")
    private String password;
}
