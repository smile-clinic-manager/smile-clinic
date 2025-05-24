package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.MedicalRecordEntryPersistancePort;
import com.smile.clinic.smile_clinic.application.ports.output.UserPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.domain.models.Tooth;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ToothEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.RecordPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.ToothPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.TreatmentPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.UserPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.RecordEntityRepository;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.ToothEntityRepository;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class MedicalRecordEntryPersistanceAdapter implements MedicalRecordEntryPersistancePort {

    private final RecordEntityRepository recordEntityRepository;
    private final UserPersistanceMapper userMapper;
    private final RecordPersistanceMapper recordPersistanceMapper;
    private final ToothEntityRepository toothEntityRepository;

    @Override
    public List<MedicalRecordEntry> findAll() {
        return recordPersistanceMapper.toMedicalRecordEntryList(this.recordEntityRepository.findAll());
    }

    @Override
    public MedicalRecordEntry findById(Long id) {
        return recordPersistanceMapper.toMedicalRecordEntry(this.recordEntityRepository.findById(id).orElse(null));
    }

    @Override
    public MedicalRecordEntry save(MedicalRecordEntry record) {
        return recordPersistanceMapper.toMedicalRecordEntry(this.recordEntityRepository.save(recordPersistanceMapper.toMedicalRecordEntryEntity(record)));
    }

    @Override
    public MedicalRecordEntry update(Long id, MedicalRecordEntry record) {
        return recordPersistanceMapper.toMedicalRecordEntry(this.recordEntityRepository.save(recordPersistanceMapper.toMedicalRecordEntryEntity(record)));
    }

    @Override
    public void delete(MedicalRecordEntry record) {
        this.recordEntityRepository.delete(recordPersistanceMapper.toMedicalRecordEntryEntity(record));
    }

    @Override
    public List<MedicalRecordEntry> findAllByMedicalHistory(Long medicalHistoryId) {
        return recordPersistanceMapper.toMedicalRecordEntryList(
                this.recordEntityRepository.findAllByMedicalHistory(medicalHistoryId));
    }

    @Override
    public MedicalRecordEntry createMedicalRecordEntry(MedicalRecordEntry medicalRecordEntry, User user) {
        MedicalRecordEntryEntity medicalRecord = recordPersistanceMapper.toMedicalRecordEntryEntity(medicalRecordEntry);
        medicalRecord.setUser(userMapper.toUserEntity(user));
        return recordPersistanceMapper.toMedicalRecordEntry(this.recordEntityRepository.save(medicalRecord));

    }



}
