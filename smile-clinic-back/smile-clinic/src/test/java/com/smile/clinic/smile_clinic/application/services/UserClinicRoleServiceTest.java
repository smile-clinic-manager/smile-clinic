package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.UserClinicRolePersistancePort;
import com.smile.clinic.smile_clinic.application.ports.output.UserPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.users.Role;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserClinicRoleServiceTest {

    @Mock
    private UserClinicRolePersistancePort userClinicRolePersistancePort;

    @Mock
    private UserPersistancePort userPersistancePort;

    @InjectMocks
    private UserClinicRoleService userClinicRoleService;

    private final Long userId = 1L;
    private final Long clinicId = 10L;
    private final Long roleId1 = 100L;
    private final Long roleId2 = 200L;

    @Test
    void createMultipleUserClinicRole_whenNoRecordExists_createsRoles() throws Exception {
        List<Long> roleIds = List.of(roleId1, roleId2);

        Mockito.when(userClinicRolePersistancePort.recordExists(userId, clinicId, roleId1)).thenReturn(false);
        Mockito.when(userClinicRolePersistancePort.recordExists(userId, clinicId, roleId2)).thenReturn(false);

        userClinicRoleService.createMultipleUserClinicRole(userId, clinicId, roleIds);

        Mockito.verify(userClinicRolePersistancePort).createUserClinicRole(userId, clinicId, roleId1);
        Mockito.verify(userClinicRolePersistancePort).createUserClinicRole(userId, clinicId, roleId2);
    }

    @Test
    void createMultipleUserClinicRole_whenRecordExists_throwsException() {
        List<Long> roleIds = List.of(roleId1);

        Mockito.when(userClinicRolePersistancePort.recordExists(userId, clinicId, roleId1)).thenReturn(true);

        Exception ex = assertThrows(Exception.class, () -> {
            userClinicRoleService.createMultipleUserClinicRole(userId, clinicId, roleIds);
        });

        assertTrue(ex.getMessage().contains("No se puede añadir el mismo usuario"));
    }

    @Test
    void createUserClinicRole_whenSuccess_callsPersistenceLayer() {
        userClinicRoleService.createUserClinicRole(userId, clinicId, roleId1);

        Mockito.verify(userClinicRolePersistancePort).createUserClinicRole(userId, clinicId, roleId1);
    }

    @Test
    void createUserClinicRole_whenExceptionOccurs_throwsRuntimeException() {
        Mockito.doThrow(new RuntimeException("DB error"))
                .when(userClinicRolePersistancePort).createUserClinicRole(userId, clinicId, roleId1);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userClinicRoleService.createUserClinicRole(userId, clinicId, roleId1);
        });

        assertEquals("DB error", ex.getMessage());
    }

    @Test
    void deleteUserClinicRole_whenSuccess_callsDelete() {
        userClinicRoleService.deleteUserClinicRole(clinicId, userId);

        Mockito.verify(userClinicRolePersistancePort).deleteUserClinicRole(userId, clinicId);
    }

    @Test
    void deleteUserClinicRole_whenExceptionOccurs_throwsRuntimeException() {
        Mockito.doThrow(new RuntimeException("Delete failed"))
                .when(userClinicRolePersistancePort).deleteUserClinicRole(userId, clinicId);

        RuntimeException ex = assertThrows(RuntimeException.class, () -> {
            userClinicRoleService.deleteUserClinicRole(clinicId, userId);
        });

        assertEquals("Delete failed", ex.getMessage());
    }

    @Test
    void updateUserClinicRole_deletesOldAndCreatesNewRoles() {
        User mockUser = User.builder().id(userId).build();
        List<Role> newRoles = List.of(
                new Role(1L, "ADMIN", List.of()),
                new Role(2L, "STAFF", List.of())
        );

        userClinicRoleService.updateUserClinicRole(mockUser, clinicId, newRoles);

        Mockito.verify(userClinicRolePersistancePort).deleteUserClinicRole(userId, clinicId);
        Mockito.verify(userClinicRolePersistancePort).createUserClinicRole(userId, clinicId, 1L);
        Mockito.verify(userClinicRolePersistancePort).createUserClinicRole(userId, clinicId, 2L);
    }

}
