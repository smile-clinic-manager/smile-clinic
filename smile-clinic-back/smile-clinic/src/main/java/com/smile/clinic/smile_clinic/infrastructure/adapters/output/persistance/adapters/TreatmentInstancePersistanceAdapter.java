package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.TreatmentInstancePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.TreatmentEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.TreatmentInstancePersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.TreatmentPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.TreatmentInstanceEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@RequiredArgsConstructor
public class TreatmentInstancePersistanceAdapter implements TreatmentInstancePersistancePort {

    private final TreatmentInstanceEntityRepository treatmentInstanceEntityRepository;
    private final TreatmentInstancePersistanceMapper treatmentInstanceMapper;
    private final TreatmentPersistanceMapper treatmentPersistanceMapper;

    @Override
    public TreatmentInstance findById(Long id) {
        return treatmentInstanceMapper.toTreatmentInstance(treatmentInstanceEntityRepository.findById(id).orElse(null));
    }

    @Override
    public List<TreatmentInstance> findAll() {
        return treatmentInstanceMapper.toTreatmentInstanceList(treatmentInstanceEntityRepository.findAll());
    }

    @Override
    public List<TreatmentInstance> findByPatientId(Long id) {
        return treatmentInstanceMapper.toTreatmentInstanceList(treatmentInstanceEntityRepository.findByPatientId(id));
    }

    @Override
    public TreatmentInstance save(TreatmentInstance treatmentInstance) {
        return treatmentInstanceMapper.toTreatmentInstance(treatmentInstanceEntityRepository.save(treatmentInstanceMapper.toTreatmentInstanceEntity(treatmentInstance)));
    }

    @Override
    public TreatmentInstance update(Long id, TreatmentInstance treatmentInstance) {
        return treatmentInstanceMapper.toTreatmentInstance(treatmentInstanceEntityRepository.save(treatmentInstanceMapper.toTreatmentInstanceEntity(treatmentInstance)));
    }

    @Override
    public void delete(TreatmentInstance treatmentInstance) {
        treatmentInstanceEntityRepository.delete(treatmentInstanceMapper.toTreatmentInstanceEntity(treatmentInstance));
    }

    @Override
    public Treatment findTreatmentToCopy(Long treatmentToCopyId) {
        TreatmentEntity t = treatmentInstanceEntityRepository.findTreatmentToCopy(treatmentToCopyId);
        return treatmentPersistanceMapper.toTreatment(t);
    }

    @Override
    public void deleteMedicalRecordsByTreatmentInstanceId(Long id) {
        this.treatmentInstanceEntityRepository.deleteMedicalRecordsByTreatmentInstanceId(id);
    }
}
