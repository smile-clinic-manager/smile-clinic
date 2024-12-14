package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest;

import com.smile.clinic.smile_clinic.application.services.AuthService;
import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationResponse;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.AuthRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.authenticationsDTO.AuthenticationRequestDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.authenticationsDTO.AuthenticationResponseDTO;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@AllArgsConstructor
public class AuthenticationController {

    private final AuthService authService;
    private final AuthRestMapper authRestMapper;

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponseDTO> login(@Valid @RequestBody AuthenticationRequestDTO authenticationRequestDTO,
                                                           BindingResult bindingResult) throws Exception {
        if(bindingResult.hasErrors()){
            AuthenticationResponseDTO response = new AuthenticationResponseDTO(bindingResult.getFieldError().getDefaultMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }

        try{
            AuthenticationResponse authenticationResponse = this.authService.login(authRestMapper.toAuthenticationRequest(authenticationRequestDTO));
            AuthenticationResponseDTO response = authRestMapper.toAuthenticationResponseDTO(authenticationResponse);
            return new ResponseEntity<>(response, HttpStatus.OK);

        } catch (Exception e){
            AuthenticationResponseDTO response = new AuthenticationResponseDTO(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/validate-token")
    public ResponseEntity<Boolean> validateToken(@RequestParam String token){
        boolean tokenIsValid = this.authService.validateToken(token);
        return new ResponseEntity<>(tokenIsValid, HttpStatus.OK);
    }
}
