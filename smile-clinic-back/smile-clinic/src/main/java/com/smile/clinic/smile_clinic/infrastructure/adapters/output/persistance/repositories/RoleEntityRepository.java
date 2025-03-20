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
    @Query(value = "SELECT DISTINCT r.* " +
            "FROM role r " +
            "INNER JOIN user_clinic_role ucr ON ucr.role_id = r.role_id " +
            "INNER JOIN clinics c ON ucr.clinic_id = c.clinic_id " +
            "INNER JOIN users u ON u.id = ucr.user_id " +
            "WHERE c.clinic_id = :clinicId " +
            "AND u.id = :userId ", nativeQuery = true)
    List<RoleEntity> findRoleUserByClinic(@Param("userId") Long userId, @Param("clinicId") Long clinicId);
}
