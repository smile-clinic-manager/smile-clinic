package com.smile.clinic.smile_clinic.domain.models;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.MedicalRecordEntryEntity;
import jakarta.persistence.ManyToMany;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Tooth {
    private Long id;
    private String code;
    private String name;
    private List<MedicalRecordEntry> medicalRecords;
}
