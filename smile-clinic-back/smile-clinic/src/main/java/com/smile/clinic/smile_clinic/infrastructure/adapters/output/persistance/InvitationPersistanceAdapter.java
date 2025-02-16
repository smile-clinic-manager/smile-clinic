package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance;

import com.smile.clinic.smile_clinic.application.ports.output.InvitationPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.invitations.Invitation;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.InvitationPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.InvitationEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class InvitationPersistanceAdapter implements InvitationPersistancePort {

    private final InvitationEntityRepository invitationRepository;
    private final InvitationPersistanceMapper mapper;

    @Override
    public List<Invitation> findAll() {
        return this.mapper.toInvitationList(this.invitationRepository.findAll());
    }

    @Override
    public Optional<Invitation> findById(Long id) {
        return this.invitationRepository.findById(id).map(this.mapper::toInvitation);
    }

    @Override
    public List<Invitation> findByClinicId(Long clinicId) {
        return this.mapper.toInvitationList(this.invitationRepository.findByClinicId(clinicId));
    }

    @Override
    public List<Invitation> findByUserId(Long userId) {
        return this.mapper.toInvitationList(this.invitationRepository.findByUserId(userId));
    }

    @Override
    public List<Invitation> findByStatus(String status) {
        return this.mapper.toInvitationList(this.invitationRepository.findByStatus(status));
    }

    @Override
    public Invitation save(Invitation invitation) {
        return this.mapper.toInvitation(this.invitationRepository.save(this.mapper.toInvitationEntity(invitation)));
    }

    @Override
    public void deleteById(Long id) {
        this.invitationRepository.deleteById(id);
    }
}
