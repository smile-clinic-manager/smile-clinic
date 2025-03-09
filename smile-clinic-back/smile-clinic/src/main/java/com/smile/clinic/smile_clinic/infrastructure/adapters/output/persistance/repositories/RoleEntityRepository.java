package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.w3c.dom.stylesheets.LinkStyle;

import java.util.List;

@Repository
public interface RoleEntityRepository extends JpaRepository<RoleEntity, Long> {
    @Query(value = "SELECT role.* FROM role " +
            " INNER JOIN user_clinic_role ucr ON ucr.user_id = role.role_id " +
            " INNER JOIN clinics ON ucr.clinic_id = clinics.clinic_id " +
            " INNER JOIN users ON users.id = ucr.user_id " +
            " WHERE users.id = :userId " +
            " AND clinics.clinic_id = :clinicId ", nativeQuery = true)
    List<RoleEntity> findRoleUserByClinic(@Param("userId") Long userId, @Param("clinicId") Long clinicId);
}
