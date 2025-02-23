package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.UserServicePort;
import com.smile.clinic.smile_clinic.application.services.UserService;
import com.smile.clinic.smile_clinic.domain.exceptions.InsecurePasswordException;
import com.smile.clinic.smile_clinic.domain.exceptions.UsernameAlreadyExistsException;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.UserRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.ErrorResponseDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.SignupRequestDTO;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.mapstruct.control.MappingControl;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.Map;
import java.util.Objects;

@Slf4j
@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserRestMapper userRestMapper;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody @Valid SignupRequestDTO signupRequestDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors() || !signupRequestDTO.getPassword().equals(signupRequestDTO.getConfirmPassword())){
            String msg = bindingResult.hasErrors() ?
                    Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage() :
                    "Las contraseñas no coinciden";
            ErrorResponseDTO response = new ErrorResponseDTO(msg);
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        if(!signupRequestDTO.getEmail().equals(signupRequestDTO.getConfirmEmail())){
            ErrorResponseDTO response = new ErrorResponseDTO("Los correos electrónicos no coinciden");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }
        try {
            User user = userRestMapper.toUser(signupRequestDTO);
            //
            Map<User, String> map = this.userService.register(user, signupRequestDTO.getPassword());
            Map.Entry<User, String> entry = map.entrySet().stream()
                    .findFirst()
                    .orElseThrow(()-> new RuntimeException("Ha ocurrido un error al registrar el usuario"));

            RegisteredUserDTO registeredUserDTO = this.userRestMapper.toRegisteredUserDTO(entry.getKey());
            String jwtToken = entry.getValue();
            registeredUserDTO.setJwtToken(jwtToken);
            //
            return new ResponseEntity<>(registeredUserDTO, HttpStatus.CREATED);

        } catch (UsernameAlreadyExistsException e) {
            ErrorResponseDTO response = new ErrorResponseDTO("Nombre de usuario ya está siendo utilizado");
            return new ResponseEntity<>(response, HttpStatus.CONFLICT);
        } catch (InsecurePasswordException e) {
            ErrorResponseDTO response = new ErrorResponseDTO("Contraseña no válida, debe contener al menos 12 caracteres, una mayúscula, una minúscula y un caracter especial");
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            ErrorResponseDTO response = new ErrorResponseDTO("Ha ocurrido un error al registrar el usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/profile")
    public ResponseEntity<RegisteredUserDTO> getProfile(Principal principal){
        log.info("Principal " + principal.toString());
        RegisteredUserDTO userDTO = this.userRestMapper.toRegisteredUserDTO(this.userService.findByUsername(principal.getName()));
        return new ResponseEntity<>(userDTO, HttpStatus.OK);
    }
}
