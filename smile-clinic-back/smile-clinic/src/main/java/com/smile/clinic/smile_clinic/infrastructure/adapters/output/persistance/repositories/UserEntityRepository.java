package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.usersDTO.RegisteredUserDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    @Query("SELECT u FROM UserEntity u WHERE u.username = :username")
    Optional<UserEntity> findUserByUsername(@Param("username") String username);

    @Query("SELECT u FROM UserEntity u WHERE u.dni = :dni")
    Optional<UserEntity> findUserByDNI(@Param("dni") String dni);

    @Query(value = "SELECT DISTINCT u.* FROM users u " +
            " INNER JOIN user_clinic_role ucr ON ucr.user_id = c.user_id " +
            " WHERE ucr.user_id = :clinicId", nativeQuery = true)
    List<RegisteredUserDTO> findUsersByClinicId(@Param("clinicId") Long clinicId);


}
