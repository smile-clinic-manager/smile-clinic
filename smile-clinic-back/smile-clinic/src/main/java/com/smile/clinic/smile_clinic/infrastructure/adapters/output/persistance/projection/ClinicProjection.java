package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.projection;

public interface ClinicProjection {
    String getId();
    String getName();
    String getAddress();
    String getPostalCode();
    String getPhoneNumber();
    String getEmail();
    String getImage();

}
