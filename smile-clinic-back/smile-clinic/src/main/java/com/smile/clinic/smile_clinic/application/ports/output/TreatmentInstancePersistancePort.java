package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;

import java.util.List;

public interface TreatmentInstancePersistancePort {
    TreatmentInstance findById(Long id);
    List<TreatmentInstance> findAll();
    List<TreatmentInstance> findByPatientId(Long id);

    TreatmentInstance save(TreatmentInstance treatmentInstance);
    TreatmentInstance update(Long id, TreatmentInstance treatmentInstance);
    void delete(TreatmentInstance treatmentInstance);

    Treatment findTreatmentToCopy(Long treatmentToCopyId);

    void deleteMedicalRecordsByTreatmentInstanceId(Long id);
}
