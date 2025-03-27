package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.TreatmentServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.TreatmentPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Treatment;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TreatmentService implements TreatmentServicePort {

    private final TreatmentPersistancePort treatmentPersistancePort;

    @Override
    public Treatment findById(Long id) {
        Optional<Treatment> treatment = treatmentPersistancePort.findById(id);
        if(treatment.isEmpty()){
            throw new NoSuchElementException("No treatment found with id " + id);
        }
        return treatment.get();
    }

    @Override
    public List<Treatment> findAll() {
        return treatmentPersistancePort.findAll();
    }

    @Override
    public List<Treatment> findByClinicId(Long id) {
        List<Treatment> treatments =  treatmentPersistancePort.findByClinicId(id);
        treatments.forEach(treatment -> treatment.setClinicId(id));
        return treatments;
    }

    @Override
    public Treatment save(Treatment treatment) {
        return treatmentPersistancePort.save(treatment);
    }

    @Override
    public Treatment update(Long id, Treatment treatment) {
        return treatmentPersistancePort.update(id, treatment);
    }

    @Override
    public void delete(Treatment treatment) {
        treatmentPersistancePort.delete(treatment);
    }

    @Override
    public Treatment create(Treatment treatment) {
        return treatmentPersistancePort.save(treatment);
    }
}
