package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest;

import com.smile.clinic.smile_clinic.application.services.InvitationService;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.InvitationRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.InvitationDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/invitations")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class InvitationController {

    //TODO implement methods

    private final InvitationService invitationService;
    private final InvitationRestMapper invitationRestMapper;

    @PostMapping("/create")
    public ResponseEntity<InvitationDTO> createInvitation(){
        return null;
    }

    @PostMapping("/accept")
    public ResponseEntity<InvitationDTO> acceptInvitation(){
        return null;
    }

    @PostMapping("/reject")
    public ResponseEntity<Void> rejectInvitation(){
        return null;
    }

    @GetMapping("/invitations")
    public ResponseEntity<List<InvitationDTO>> listInvitations(){
        return null;
    }

    @GetMapping("/invitations/{id}")
    public ResponseEntity<InvitationDTO> detailedInvitation(){
        return null;
    }

}
