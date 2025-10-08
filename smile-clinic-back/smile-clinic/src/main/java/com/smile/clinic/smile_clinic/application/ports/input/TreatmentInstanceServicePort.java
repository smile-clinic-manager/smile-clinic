package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;

import java.util.List;

public interface TreatmentInstanceServicePort {

    TreatmentInstance findById(Long id);
    List<TreatmentInstance> findAll();
    List<TreatmentInstance> findByPatientId(Long id);

    TreatmentInstance save(TreatmentInstance treatmentInstance);
    TreatmentInstance update(Long id, TreatmentInstance treatmentInstance);
    void delete(TreatmentInstance treatmentInstance);

    void deleteByPatientId(Long id);
}
