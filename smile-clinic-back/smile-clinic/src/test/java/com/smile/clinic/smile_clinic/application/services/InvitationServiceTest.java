package com.smile.clinic.smile_clinic.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import com.smile.clinic.smile_clinic.application.ports.output.InvitationPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.InvitationNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.invitations.Invitation;
import org.junit.jupiter.api.*;
import org.mockito.*;

class InvitationServiceTest {

    @Mock
    private InvitationPersistancePort invitationPersistancePort;

    @InjectMocks
    private InvitationService invitationService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void findById_found() {
        Long id = 1L;
        Invitation invitation = new Invitation();
        invitation.setId(id);

        when(invitationPersistancePort.findById(id)).thenReturn(Optional.of(invitation));

        Invitation result = invitationService.findById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findById_notFound_throws() {
        Long id = 1L;
        when(invitationPersistancePort.findById(id)).thenReturn(Optional.empty());

        InvitationNotFoundException ex = assertThrows(InvitationNotFoundException.class, () -> invitationService.findById(id));
        assertEquals("Invitation with id 1 not found", ex.getMessage());
    }

    @Test
    void findByClinicId_returnsList() {
        Long clinicId = 10L;
        List<Invitation> invitations = List.of(new Invitation(), new Invitation());

        when(invitationPersistancePort.findByClinicId(clinicId)).thenReturn(invitations);

        List<Invitation> result = invitationService.findByClinicId(clinicId);

        assertEquals(2, result.size());
    }

    @Test
    void findByUserId_returnsList() {
        Long userId = 20L;
        List<Invitation> invitations = List.of(new Invitation());

        when(invitationPersistancePort.findByUserId(userId)).thenReturn(invitations);

        List<Invitation> result = invitationService.findByUserId(userId);

        assertEquals(1, result.size());
    }

    @Test
    void findByStatus_returnsList() {
        String status = "PENDING";
        List<Invitation> invitations = List.of(new Invitation());

        when(invitationPersistancePort.findByStatus(status)).thenReturn(invitations);

        List<Invitation> result = invitationService.findByStatus(status);

        assertEquals(1, result.size());
    }

    @Test
    void findAll_returnsList() {
        List<Invitation> invitations = List.of(new Invitation(), new Invitation(), new Invitation());

        when(invitationPersistancePort.findAll()).thenReturn(invitations);

        List<Invitation> result = invitationService.findAll();

        assertEquals(3, result.size());
    }

    @Test
    void create_savesAndReturns() {
        Invitation invitation = new Invitation();
        invitation.setId(5L);

        when(invitationPersistancePort.save(invitation)).thenReturn(invitation);

        Invitation result = invitationService.create(invitation);

        assertNotNull(result);
        assertEquals(5L, result.getId());
    }

    @Test
    void update_savesAndReturns() {
        Invitation invitation = new Invitation();
        invitation.setId(6L);

        when(invitationPersistancePort.save(invitation)).thenReturn(invitation);

        Invitation result = invitationService.update(invitation);

        assertNotNull(result);
        assertEquals(6L, result.getId());
    }

    @Test
    void deleteById_existingInvitation_deletes() {
        Long id = 7L;

        when(invitationPersistancePort.findById(id)).thenReturn(Optional.of(new Invitation()));

        invitationService.deleteById(id);

        verify(invitationPersistancePort).deleteById(id);
    }

    @Test
    void deleteById_notFound_throws() {
        Long id = 8L;

        when(invitationPersistancePort.findById(id)).thenReturn(Optional.empty());

        InvitationNotFoundException ex = assertThrows(InvitationNotFoundException.class, () -> invitationService.deleteById(id));
        assertEquals("Invitation with id 8 not found", ex.getMessage());
    }
}
