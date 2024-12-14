package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.Treatment;

import java.util.List;
import java.util.Optional;

public interface TreatmentServicePort {
    Treatment findById(Long id);
    List<Treatment> findAll();
    Treatment save(Treatment treatment);
    Treatment update(Long id, Treatment treatment);
    void delete(Treatment treatment);
}
