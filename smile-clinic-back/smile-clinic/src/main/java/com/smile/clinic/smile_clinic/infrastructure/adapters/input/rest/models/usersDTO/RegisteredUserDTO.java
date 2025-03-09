package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO;

import com.smile.clinic.smile_clinic.domain.models.users.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisteredUserDTO implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    private Long id;

    private String username;

    private String firstName;

    private String lastName1;

    private String lastName2;

    private String dni;

    private List<Role> roles;

    private String jwtToken;

}
