package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "permissions")
public class PermissionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_permission")
    @SequenceGenerator(name="seq_permission", sequenceName = "seq_permission", allocationSize = 1)
    @Column(name = "permission_id")
    private Long id;

    @NotNull
    private String name;

    @ManyToMany(mappedBy = "permissions")
    private List<RoleEntity> roles;
}
