package com.smile.clinic.smile_clinic.utils;

import com.smile.clinic.smile_clinic.application.ports.output.AuthenticationProviderPort;
import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationRequest;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class AuthenticationAdapter implements AuthenticationProviderPort {

    private final AuthenticationManager authenticationManager;
    
    public void authenticate(AuthenticationRequest authenticationRequest){
        Authentication authentication = new UsernamePasswordAuthenticationToken(
                authenticationRequest.getUsername(),
                authenticationRequest.getPassword()
        );

        this.authenticationManager.authenticate(authentication);
    }

}
