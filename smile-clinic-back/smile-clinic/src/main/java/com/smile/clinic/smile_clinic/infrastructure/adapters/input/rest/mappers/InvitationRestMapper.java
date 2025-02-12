package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.invitations.Invitation;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.InvitationDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface InvitationRestMapper {

    Invitation toInvitation(InvitationDTO invitationDTO);
    InvitationDTO toInvitationDTO(Invitation invitation);

    List<Invitation> toInvitationList(List<InvitationDTO> invitationsDTO);
    List<InvitationDTO> toInvitationDTOList(List<Invitation> invitations);

}
