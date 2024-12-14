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
    private Long id;

    @NotBlank
    private String name;

    @NotBlank
    private String address;

    @NotBlank
    private String phone;

    @Email
    @NotBlank
    private String email;

    private String website;

    private String description;

    private String image;

    @ManyToOne
    @JoinColumn(name = "owner_id", nullable = false)
    private UserEntity owner;

    // Relación con empleados
    @ManyToMany
    @JoinTable(
            name = "clinic_employees",
            joinColumns = @JoinColumn(name = "clinic_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private List<UserEntity> employees;


}
