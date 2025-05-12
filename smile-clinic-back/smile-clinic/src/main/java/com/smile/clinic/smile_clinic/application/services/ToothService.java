package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.ToothServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.ToothServicePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Tooth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToothService implements ToothServicePort {
    private final ToothServicePersistancePort toothServicePersistancePort;

    @Override
    public List<Tooth> findAllTeeth() {
        List<Tooth> teeth = this.toothServicePersistancePort.findAllTeeth();
        return teeth;
    }
}
