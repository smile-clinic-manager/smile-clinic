package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.ToothServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.ToothServicePersistancePort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ToothService implements ToothServicePort {
    private final ToothServicePersistancePort toothServicePersistancePort;
}
