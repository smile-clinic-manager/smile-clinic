package com.smile.clinic.smile_clinic.utils;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import com.smile.clinic.smile_clinic.application.ports.output.PasswordEncoderPort;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class BCryptPasswordEncoderAdapter implements PasswordEncoderPort {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    @Override
    public String encode(String password) {
        return bCryptPasswordEncoder.encode(password);
    }

    @Override
    public boolean matches(String passwordToValidate, String encodedPassword) {
        return bCryptPasswordEncoder.matches(passwordToValidate, encodedPassword);
    }
}
