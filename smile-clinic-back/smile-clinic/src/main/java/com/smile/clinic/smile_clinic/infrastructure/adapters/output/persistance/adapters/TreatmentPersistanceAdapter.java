package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.TreatmentPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ClinicEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.TreatmentEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.ClinicPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.TreatmentPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.ClinicEntityRepository;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.TreatmentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TreatmentPersistanceAdapter implements TreatmentPersistancePort {

    private final TreatmentEntityRepository treatmentEntityRepository;
    private final ClinicEntityRepository clinicEntityRepository;
    private final TreatmentPersistanceMapper treatmentPersistanceMapper;
    private final ClinicPersistanceMapper clinicPersistanceMapper;

    @Override
    public Optional<Treatment> findById(Long id) {
        Optional<TreatmentEntity> t = treatmentEntityRepository.findById(id);
        return this.treatmentEntityRepository.findById(id).map(treatmentPersistanceMapper::toTreatment);
    }

    @Override
    public List<Treatment> findAll() {
        return treatmentPersistanceMapper.toTreatmentList(this.treatmentEntityRepository.findAll());
    }

    @Override
    public Treatment save(Treatment treatment) {
        TreatmentEntity treatmentEntity = treatmentPersistanceMapper.toTreatmentEntity(treatment);
        Optional<ClinicEntity> clinicEntity = clinicEntityRepository.findById(treatment.getClinicId());
        if(clinicEntity.isEmpty()){
            throw new RuntimeException("Clínica no encontrada");
        }
        treatmentEntity.setClinic(clinicEntity.get());

        return treatmentPersistanceMapper.toTreatment(this.treatmentEntityRepository.save(treatmentEntity));
    }

    @Override
    public Treatment update(Long id, Treatment treatment) {
        return treatmentEntityRepository.findById(id)
                .map(existingEntity -> {
                    TreatmentEntity updatedEntity = treatmentPersistanceMapper.toTreatmentEntity(treatment);
                    updatedEntity.setId(existingEntity.getId());
                    updatedEntity.setClinic(existingEntity.getClinic());

                    return treatmentPersistanceMapper.toTreatment(
                            treatmentEntityRepository.save(updatedEntity)
                    );
                })
                .orElseThrow(() -> new RuntimeException("Treatment with ID " + id + " not found"));

    }

    @Override
    public void delete(Treatment treatment) {
        this.treatmentEntityRepository.delete(treatmentPersistanceMapper.toTreatmentEntity(treatment));
    }

    @Override
    public List<Treatment> findByClinicId(Long clinicId) {
        return treatmentPersistanceMapper.toTreatmentList(this.treatmentEntityRepository.findByClinicId(clinicId));
    }
}
