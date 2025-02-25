package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.AuthServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.UserServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.AuthenticationProviderPort;
import com.smile.clinic.smile_clinic.application.ports.output.TokenProviderPort;
import com.smile.clinic.smile_clinic.application.ports.output.UserPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationRequest;
import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationResponse;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import io.jsonwebtoken.Claims;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class AuthService implements AuthServicePort {

    private final AuthenticationProviderPort authenticationProviderPort;
    private final UserPersistancePort userPersistancePort;
    private final TokenProviderPort tokenProviderPort;

    public AuthenticationResponse login(AuthenticationRequest authRequest) throws Exception {
        //Check if exists
        Optional<User> user = this.userPersistancePort.findUserByUsername(authRequest.getUsername());
        if(user.isEmpty()) throw new Exception("El usuario o la contraseña son incorrectos.");

        //Authenticate user
        this.authenticationProviderPort.authenticate(authRequest);
        AuthenticationResponse authenticationResponse = new AuthenticationResponse(this.tokenProviderPort.generateToken(user.get()));
        return authenticationResponse;
    }

    @Override
    public Boolean validateToken(String token){
        try{
            Claims claims = this.tokenProviderPort.getAllTokenClaims(token); //If we can extract the data it is a valid token
            return true;
        } catch (Exception e){
            log.info(e.getMessage());
            return false;
        }
    }

}
