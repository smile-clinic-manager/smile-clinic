package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.patients.Disease;

import java.util.List;

public interface DiseaseServicePort {
    List<Disease> findAll();
    Disease findById(Long id);
    Disease save(Disease disease);
    Disease update(Disease disease);
    void deleteById(Long id);

}
