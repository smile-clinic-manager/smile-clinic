package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.InvitationEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InvitationEntityRepository extends JpaRepository<InvitationEntity, Long> {

    @Query("SELECT i FROM InvitationEntity i WHERE i.clinic.id = :clinicId")
    List<InvitationEntity> findByClinicId(Long clinicId);

    @Query("SELECT i FROM InvitationEntity i WHERE i.user.id = :userId")
    List<InvitationEntity> findByUserId(Long userId);

    @Query("SELECT i FROM InvitationEntity i WHERE i.status = :status")
    List<InvitationEntity> findByStatus(String status);
}
