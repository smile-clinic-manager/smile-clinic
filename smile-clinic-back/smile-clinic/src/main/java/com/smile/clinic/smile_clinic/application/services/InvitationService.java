package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.InvitationServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.InvitationPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.InvitationNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.invitations.Invitation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class InvitationService implements InvitationServicePort {

    private final InvitationPersistancePort invitationPersistancePort;

    @Override
    public Invitation findById(Long id) {
        return this.invitationPersistancePort.findById(id)
                .orElseThrow(()-> new InvitationNotFoundException("Invitation with id "+id+" not found"));
    }

    @Override
    public List<Invitation> findByClinicId(Long clinicId) {
        return this.invitationPersistancePort.findByClinicId(clinicId);
    }

    @Override
    public List<Invitation> findByUserId(Long userId) {
        return this.invitationPersistancePort.findByUserId(userId);
    }

    @Override
    public List<Invitation> findByStatus(String status) {
        return this.invitationPersistancePort.findByStatus(status);
    }

    @Override
    public List<Invitation> findAll() {
        return this.invitationPersistancePort.findAll();
    }

    @Override
    public Invitation create(Invitation invitation) {
        return this.invitationPersistancePort.save(invitation);
    }

    @Override
    public Invitation update(Invitation invitation) {
        return this.invitationPersistancePort.save(invitation);
    }

    @Override
    public void deleteById(Long id) {
        if(invitationPersistancePort.findById(id).isEmpty()){
            throw new InvitationNotFoundException("Invitation with id "+id+" not found");
        }
        invitationPersistancePort.deleteById(id);
    }
}
