package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.MedicalHistoryServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.MedicalRecordEntryServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.MedicalRecordEntryPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.domain.models.Tooth;
import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalRecordEntryFormDTO;
import com.smile.clinic.smile_clinic.utils.DateTimeComposer;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MedicalRecordEntryService implements MedicalRecordEntryServicePort {

    private final MedicalRecordEntryPersistancePort medicalRecordEntryPersistancePort;
    private final TreatmentInstanceService treatmentInstanceService;
    private final UserService userService;
    private final MedicalHistoryService medicalHistoryService;
    private final DateTimeComposer dateTimeComposer;
    private final ToothService toothService;

    @Override
    public List<MedicalRecordEntry> findAll() {
        return medicalRecordEntryPersistancePort.findAll();
    }

    @Override
    public MedicalRecordEntry findById(Long id) {
        return medicalRecordEntryPersistancePort.findById(id);
    }

    @Override
    public MedicalRecordEntry save(MedicalRecordEntry MedicalRecordEntry) {
        return medicalRecordEntryPersistancePort.save(MedicalRecordEntry);
    }

    @Override
    public MedicalRecordEntry update(Long id, MedicalRecordEntry MedicalRecordEntry) {
        return medicalRecordEntryPersistancePort.update(id, MedicalRecordEntry);
    }

    @Override
    public void delete(MedicalRecordEntry MedicalRecordEntry) {
        medicalRecordEntryPersistancePort.delete(MedicalRecordEntry);
    }

    @Override
    public List<MedicalRecordEntry> findAllByMedicalHistory(Long medicalHistoryId) {
        return medicalRecordEntryPersistancePort.findAllByMedicalHistory(medicalHistoryId);
    }

    @Override
    public MedicalRecordEntry createMedicalRecordEntry(MedicalRecordEntryFormDTO medicalRecordEntryForm) {
        TreatmentInstance treatmentInstance = this.treatmentInstanceService.createInstanceFromTreatmentDTO(medicalRecordEntryForm.getTreatmentId());
        User user = this.userService.findUserByUserId(medicalRecordEntryForm.getUserId());
        List<Tooth> teethList = new ArrayList<>();
        for(String toothId : medicalRecordEntryForm.getTeethListId()){
            Tooth tooth = toothService.findToothById(Long.valueOf(toothId));
            teethList.add(tooth);
        }

        LocalDateTime dateTime = this.dateTimeComposer.composeDateTime(medicalRecordEntryForm.getDate(),medicalRecordEntryForm.getTime());

        MedicalRecordEntry medicalRecordEntry = MedicalRecordEntry.builder()
                .observations(medicalRecordEntryForm.getObservations())
                .dateTime(dateTime)
                .treatmentInstance(treatmentInstance)
                .build();
        MedicalRecordEntry record = this.medicalRecordEntryPersistancePort.createMedicalRecordEntry(medicalRecordEntry, user);

        // Asociamos al medicalHistory
        MedicalHistory medicalHistory = this.medicalHistoryService.findMedicalHistoryById(medicalRecordEntryForm.getMedicalHistoryId());
        return record;
    }
}
