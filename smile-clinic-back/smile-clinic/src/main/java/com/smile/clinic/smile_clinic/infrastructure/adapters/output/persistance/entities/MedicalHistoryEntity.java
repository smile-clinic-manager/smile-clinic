package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "medical_history")
public class MedicalHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_history_seq")
    @SequenceGenerator(name = "medical_history_seq", sequenceName = "medical_history_seq", allocationSize = 1)
    private Long id;

    private String allergies;

    @ManyToMany
    @JoinTable(
            name = "medical_history_previous_diseases",
            joinColumns = @JoinColumn(name = "medical_history_id"),
            inverseJoinColumns = @JoinColumn(name = "previous_diseases_id")
    )
    private List<PreviousDiseasesEntity> previousDiseases;

    @OneToOne(mappedBy = "medicalHistory")
    private PatientEntity patient;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "medical_record_id")
    private List<MedicalRecordEntryEntity> medicalRecordEntries;
}
