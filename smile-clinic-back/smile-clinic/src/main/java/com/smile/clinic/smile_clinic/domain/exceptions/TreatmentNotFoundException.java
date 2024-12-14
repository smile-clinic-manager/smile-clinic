package com.smile.clinic.smile_clinic.domain.exceptions;

public class TreatmentNotFoundException extends RuntimeException {
    public TreatmentNotFoundException(String message) {
        super(message);
    }
}
