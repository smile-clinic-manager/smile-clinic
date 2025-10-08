package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.users.User;
import io.jsonwebtoken.Claims;

import javax.crypto.SecretKey;

public interface TokenProviderPort {
    String generateToken(User user);

    String generateRefreshToken(User user);

    SecretKey generateKey();

    Claims getAllTokenClaims(String token);

}
