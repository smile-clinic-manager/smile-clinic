package com.smile.clinic.smile_clinic.domain.exceptions;

public class StudentNotFoundException extends RuntimeException{
    public StudentNotFoundException(String message) {
        super(message);
    }

}
