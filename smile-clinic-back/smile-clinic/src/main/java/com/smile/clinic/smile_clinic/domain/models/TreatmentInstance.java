package com.smile.clinic.smile_clinic.domain.models;

import com.smile.clinic.smile_clinic.domain.models.patients.Patient;

public class TreatmentInstance {
    private Long id;
    private String name;
    private Double price;
    private String notes;
    private Patient patient;
}
