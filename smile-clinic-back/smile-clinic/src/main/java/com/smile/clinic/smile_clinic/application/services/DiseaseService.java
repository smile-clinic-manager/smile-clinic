package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.DiseaseServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.DiseasePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.patients.Disease;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class DiseaseService implements DiseaseServicePort {

    private final DiseasePersistancePort diseasePersistancePort;

    @Override
    public List<Disease> findAll() {
        return null;
    }

    @Override
    public Disease findById(Long id) {
        return null;
    }

    @Override
    public Disease save(Disease disease) {
        return null;
    }

    @Override
    public Disease update(Disease disease) {
        return null;
    }

    @Override
    public void deleteById(Long id) {

    }
}
