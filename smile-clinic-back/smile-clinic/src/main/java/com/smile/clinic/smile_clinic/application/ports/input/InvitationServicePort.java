package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.invitations.Invitation;

import java.util.List;

public interface InvitationServicePort {

    Invitation findById(Long id);
    List<Invitation> findByClinicId(Long clinicId);
    List<Invitation> findByUserId(Long userId);
    List<Invitation> findByStatus(String status);
    List<Invitation> findAll();

    Invitation create(Invitation invitation);
    Invitation update(Invitation invitation);
    void deleteById(Long id);

}
