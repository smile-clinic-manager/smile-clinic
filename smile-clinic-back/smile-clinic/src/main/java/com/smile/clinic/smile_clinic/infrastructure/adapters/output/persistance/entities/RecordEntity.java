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
@Table(name = "records")
public class RecordEntity {

        @Id
        @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "record_seq")
        @SequenceGenerator(name = "record_seq", sequenceName = "record_seq", allocationSize = 1)
        private int id;

        @NotBlank
        private int patientId; //Patient patient ?

        @NotBlank
        private int userId;

        @NotNull
        private String treatmentIdentifier; //blank si no se hace nada

        @NotNull
        private LocalDateTime dateTime;

        @NotBlank
        private String notes;
}
