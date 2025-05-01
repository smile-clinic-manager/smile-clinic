package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.adapters;

import com.smile.clinic.smile_clinic.application.ports.output.ToothServicePersistancePort;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.ToothEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ToothPersistanceAdapter implements ToothServicePersistancePort {
    private final ToothEntityRepository toothEntityRepository;
}
