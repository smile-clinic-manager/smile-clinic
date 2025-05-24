package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.domain.models.Tooth;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MedicalRecordEntryPersistancePort {

    List<MedicalRecordEntry> findAll();
    MedicalRecordEntry findById(Long id);
    MedicalRecordEntry save(MedicalRecordEntry record);
    MedicalRecordEntry update(Long id, MedicalRecordEntry record);
    void delete(MedicalRecordEntry record);

    List<MedicalRecordEntry> findAllByMedicalHistory(Long medicalHistoryId);

    MedicalRecordEntry createMedicalRecordEntry(MedicalRecordEntry medicalRecordEntry, User user);
}
