package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.MedicalRecordEntryServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.ToothServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.MedicalRecordEntryPersistancePort;
import com.smile.clinic.smile_clinic.application.ports.output.ToothServicePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.domain.models.Tooth;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ToothService implements ToothServicePort {
    private final ToothServicePersistancePort toothServicePersistancePort;
    private final MedicalRecordEntryPersistancePort medicalRecordEntryPersistancePort;

    @Override
    public List<Tooth> findAllTeeth(Long medicalHistoryId) {
        return this.toothServicePersistancePort.findAllTeeth();
    }

    @Override
    public List<Tooth> getToothEntities() {
        return this.toothServicePersistancePort.getToothEntities();
    }
}
