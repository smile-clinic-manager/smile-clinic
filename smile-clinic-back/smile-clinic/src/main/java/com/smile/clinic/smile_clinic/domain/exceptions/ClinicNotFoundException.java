package com.smile.clinic.smile_clinic.domain.exceptions;

public class ClinicNotFoundException extends RuntimeException {
    public ClinicNotFoundException(String message) {
        super(message);
    }
}
