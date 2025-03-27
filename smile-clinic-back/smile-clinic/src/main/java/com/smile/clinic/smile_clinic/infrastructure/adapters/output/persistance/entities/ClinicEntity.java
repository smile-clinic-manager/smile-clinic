package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "clinics")
public class ClinicEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "clinic_seq")
    @SequenceGenerator(name = "clinic_seq", sequenceName = "clinic_seq", allocationSize = 1)
    @Column(name = "clinic_id")
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String postalCode;

    @NotBlank
    private String phoneNumber;

    @Email
    @NotBlank
    private String email;

    private String image;

    // Relationships
    @OneToMany(mappedBy = "clinic", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<TreatmentEntity> treatments;

}
