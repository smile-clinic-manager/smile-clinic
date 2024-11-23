package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationRequest;
import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationResponse;

public interface AuthServicePort {
    AuthenticationResponse login(AuthenticationRequest authenticationRequest) throws Exception;
}
