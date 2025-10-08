package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.patients.Disease;

import java.util.List;
import java.util.Optional;

public interface DiseasePersistancePort {
    Optional<Disease> save();
    Optional<Disease> update();
    List<Disease> findAll();
    Optional<Disease> findById();
}
