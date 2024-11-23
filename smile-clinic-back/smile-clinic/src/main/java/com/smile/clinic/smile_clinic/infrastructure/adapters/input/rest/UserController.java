package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest;

import com.smile.clinic.smile_clinic.application.ports.input.UserServicePort;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.UserRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.SaveUserDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.mapstruct.control.MappingControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserServicePort userServicePort;
    private final UserRestMapper userRestMapper;

    @PostMapping("/register")
    public ResponseEntity<RegisteredUserDTO> registerUser(@RequestBody @Valid SaveUserDTO saveUserDTO, BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            String msg = bindingResult.getFieldError().getDefaultMessage();
        }

        // User has no password, so we pass it along as separate variable
        Map<User, String> map = this.userServicePort.register(this.userRestMapper.toUser(saveUserDTO), saveUserDTO.getPassword());

        Map.Entry<User, String> entry = map.entrySet().stream()
                .findFirst()
                .orElseThrow(()-> new IllegalStateException("No user was registered"));

        RegisteredUserDTO registeredUserDTO = this.userRestMapper.toRegisteredUserDTO(entry.getKey());
        String jwtToken = entry.getValue();
        registeredUserDTO.setJwtToken(jwtToken);

        return new ResponseEntity<>(registeredUserDTO, HttpStatus.CREATED);
    }
}
