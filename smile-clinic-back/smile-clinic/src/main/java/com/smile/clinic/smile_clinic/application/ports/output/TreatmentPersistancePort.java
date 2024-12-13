package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.Treatment;

import java.util.List;
import java.util.Optional;

public interface TreatmentPersistancePort {

    List<Treatment> findAll();

    Optional<Treatment> findByIdentifier(String identifier);
    List<Treatment> findIfNameContains(String substring);
    List<Treatment> findByPriceBetween(double minPrice, double maxPrice);
    List<Treatment> findIfDescriptionContains(String substring);

    Treatment save(Treatment treatment);
    Treatment update(String identifier, Treatment treatment);
    void delete(Treatment treatment);
}
