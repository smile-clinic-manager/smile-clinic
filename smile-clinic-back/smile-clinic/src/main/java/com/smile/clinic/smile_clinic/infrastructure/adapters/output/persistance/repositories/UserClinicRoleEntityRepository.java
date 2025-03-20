package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserClinicRoleEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserClinicRoleEntityRepository extends JpaRepository<UserClinicRoleEntity, Long> {

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_clinic_role (user_clinic_role_id, user_id, clinic_id, role_id) " +
            "VALUES (NEXTVAL('seq_user_clinic_role'), :userId, :clinicId, :roleId)", nativeQuery = true)
    void createUserClinicRole(@Param("userId") Long userId, @Param("clinicId") Long clinicId, @Param("roleId") Long roleId);

    @Query("SELECT COUNT(u) FROM UserClinicRoleEntity u WHERE u.user.id = :userId AND u.clinic.id = :clinicId AND u.role.id = :roleId")
    long recordExists(@Param("userId") Long userId, @Param("clinicId") Long clinicId, @Param("roleId") Long roleId);

    @Transactional
    @Modifying
    @Query(value = "DELETE FROM user_clinic_role ucr WHERE ucr.user_id = :userId AND ucr.clinic_id = :clinicId", nativeQuery = true)
    void deleteUserClinicRole(@Param("userId") Long userId, @Param("clinicId") Long clinicId);

    @Query(value = "SELECT * FROM user_clinic_role ucr " +
            "WHERE ucr.user_id = :userId " +
            "AND ucr.clinic_id = :clinicId", nativeQuery = true)
    UserClinicRoleEntity findUserClinicRoleByClinicIdUserId(@Param("userId") Long userId, @Param("clinicId") Long clinicId);
}
