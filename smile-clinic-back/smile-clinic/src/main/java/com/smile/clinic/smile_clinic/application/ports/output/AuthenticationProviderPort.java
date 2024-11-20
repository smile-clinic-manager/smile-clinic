package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.auth.AuthenticationRequest;

public interface AuthenticationProviderPort {

    void authenticate(AuthenticationRequest authenticationRequest);

}
