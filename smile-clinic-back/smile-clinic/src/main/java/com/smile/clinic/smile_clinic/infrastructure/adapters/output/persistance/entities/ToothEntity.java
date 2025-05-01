package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tooth")
public class ToothEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "tooth_seq")
    @SequenceGenerator(name = "tooth_seq", sequenceName = "tooth_seq", allocationSize = 1)
    private Long id;

    @NotBlank
    private String code;

    @NotBlank
    private String name;

    @ManyToMany(mappedBy = "teeth")
    private List<MedicalRecordEntryEntity> medicalRecords;

}
