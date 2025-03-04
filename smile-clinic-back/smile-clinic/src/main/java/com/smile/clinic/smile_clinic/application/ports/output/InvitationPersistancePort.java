package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.invitations.Invitation;

import java.util.List;
import java.util.Optional;

public interface InvitationPersistancePort {

    List<Invitation> findAll();

    Optional<Invitation> findById(Long id);
    List<Invitation> findByClinicId(Long clinicId);
    List<Invitation> findByUserId(Long userId);
    List<Invitation> findByStatus(String status);

    Invitation save(Invitation invitation);
    void deleteById(Long id);

}
