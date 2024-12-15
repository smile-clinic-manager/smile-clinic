package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import com.smile.clinic.smile_clinic.domain.models.Treatment;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "medical_records")
public class MedicalRecordEntryEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "medical_record_seq")
        @SequenceGenerator(name = "medical_record_seq", sequenceName = "medical_record_seq", allocationSize = 1)
        private int id;

        @NotNull
        private LocalDateTime dateTime;

        @NotBlank
        private String visitPurpose;

        @NotBlank
        private String observations;

        @OneToOne
        private UserEntity dentist;

        @ManyToOne
        private TreatmentEntity treatment;

}