package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.ToothServicePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Tooth;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ToothEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.ToothPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.MedicalHistoryEntityRepository;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.ToothEntityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Component
@AllArgsConstructor
public class ToothPersistanceAdapter implements ToothServicePersistancePort {
    private final ToothEntityRepository toothEntityRepository;
    private final MedicalHistoryEntityRepository medicalHistoryEntityRepository;
    private final ToothPersistanceMapper toothPersistanceMapper;

    @Override
    public List<Tooth> findAllTeeth(Long medicalHistoryId) {
        List<Long> recordIdList = medicalHistoryEntityRepository.findMedicalRecordsByHistoryId(medicalHistoryId).stream().map(MedicalRecordEntryEntity::getId).toList();
        List<ToothEntity> entities = this.toothEntityRepository.findAll();
        List<Tooth> teeth = this.toothPersistanceMapper.toToothList(entities);
        return teeth;
    }

    @Override
    public List<Tooth> getToothEntities() {
        List<ToothEntity> entities = this.toothEntityRepository.findAllToothEntities();
        List<Tooth> teeth = this.toothPersistanceMapper.toToothList(entities);
        return teeth;
    }

    @Override
    public Tooth findToothById(Long toothId) {
        return this.toothPersistanceMapper.toTooth(
                this.toothEntityRepository.findById(toothId).orElseThrow()
        );
    }
}
