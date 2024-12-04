package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.Clinic;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface ClinicServicePort {

    List<Clinic> findAll();

    Clinic findById(Long id);
    List<Clinic> findByOwnerId(int ownerId);
    Clinic findByAddress(String address);

    Clinic save(Clinic clinic);
    Clinic update(Long id, Clinic clinic);
    void deleteClinicById(Long id);
}
