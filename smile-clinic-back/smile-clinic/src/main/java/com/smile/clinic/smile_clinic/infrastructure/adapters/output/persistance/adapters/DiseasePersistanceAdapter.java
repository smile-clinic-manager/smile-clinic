package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.DiseasePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.patients.Disease;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.DiseasePersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.UserPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.DiseaseEntityRepository;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class DiseasePersistanceAdapter implements DiseasePersistancePort {
    private final DiseaseEntityRepository userRepository;
    private final DiseasePersistanceMapper mapper;

    @Override
    public Optional<Disease> save() {
        return Optional.empty();
    }

    @Override
    public Optional<Disease> update() {
        return Optional.empty();
    }

    @Override
    public List<Disease> findAll() {
        return null;
    }

    @Override
    public Optional<Disease> findById() {
        return Optional.empty();
    }
}
