package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Entity
@Getter
@Setter
@Table(name="user_clinic_role",
    uniqueConstraints = { // Make sure no user has repeated role in the same clinic
        @UniqueConstraint(columnNames = {"user_id", "clinic_id", "role_id"})
    },
    indexes = { // Improve performance by indexing in the db table
        @Index(name = "idx_user_clinic_role", columnList = "user_id, clinic_id, role_id")
    })
public class UserClinicRoleEntity {
    @Id
    @GeneratedValue(generator = "seq_user_clinic_role")
    @SequenceGenerator(name = "seq_user_clinic_role", sequenceName = "seq_user_clinic_role", allocationSize = 1)
    @Column(name = "user_clinic_role_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "clinic_id", nullable = false)
    private ClinicEntity clinic;

    @ManyToOne
    @JoinColumn(name = "role_id", nullable = false)
    private RoleEntity role;

}
