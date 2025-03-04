package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.Clinic;

import java.util.List;
import java.util.Optional;

public interface ClinicPersistancePort {

    List<Clinic> findAll();

    Optional<Clinic> findById(Long id);
    Optional<Clinic> findByAddress(String address);

    Clinic save(Clinic clinic);
    void deleteById(Long id);
}
