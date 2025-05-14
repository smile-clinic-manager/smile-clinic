package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models;

import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ToothDTO implements Serializable {
    private Long id;
    private String code;
    private String name;
    private List<MedicalRecordEntryDTO> medicalRecords;
}
