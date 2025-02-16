package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import com.smile.clinic.smile_clinic.domain.models.invitations.InvitationStatus;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "invitations")
public class InvitationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "invitation_seq")
    @SequenceGenerator(name = "invitation_seq", sequenceName = "invitation_seq", allocationSize = 1)
    private Long id;

    @Enumerated(EnumType.STRING)
    private InvitationStatus status;

    // Relationships
    @OneToOne
    @JoinColumn(name = "clinic_id")
    private ClinicEntity clinic;

    @OneToOne
    @JoinColumn(name = "user_id")
    private UserEntity user;
}
