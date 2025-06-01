package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import com.smile.clinic.smile_clinic.domain.models.appointments.AppointmentState;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.service.annotation.GetExchange;

import java.time.LocalDateTime;

@Slf4j
@Getter
@Setter
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "appointments")
public class AppointmentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "appointment_seq")
    @SequenceGenerator(name="appointment_seq", sequenceName = "appointment_seq", allocationSize = 1)
    private Long id;

    private double duration;

    @NotBlank
    private String visitPurpose;

    @NotNull
    private LocalDateTime dateTime;

    //Relations
    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
    
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private PatientEntity patient;
}
