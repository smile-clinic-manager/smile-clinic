package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.invitations.Invitation;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.InvitationEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface InvitationPersistanceMapper {

    InvitationEntity toInvitationEntity(Invitation invitation);
    Invitation toInvitation(InvitationEntity invitationEntity);

    List<Invitation> toInvitationList(List<InvitationEntity> invitationEntities);
    List<InvitationEntity> toInvitationEntityList(List<Invitation> invitations);

}
