package com.smile.clinic.smile_clinic.application.ports.output;

public interface PasswordEncoderPort {
    String encode(String password);
    boolean matches(String passwordToValidate, String encodedPassword);

}
