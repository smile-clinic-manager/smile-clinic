package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.RolePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.users.Permission;
import com.smile.clinic.smile_clinic.domain.models.users.Role;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class RoleServiceTest {

    @Mock
    private RolePersistancePort rolePersistancePort;

    @InjectMocks
    private RoleService roleService;

    private Role role1;
    private Role role2;

    @BeforeEach
    void setUp() {
        Permission perm1 = new Permission();
        perm1.setId(1L);
        perm1.setName("READ");

        Permission perm2 = new Permission();
        perm2.setId(2L);
        perm2.setName("WRITE");

        role1 = Role.builder()
                .id(1L)
                .name("ADMIN")
                .permission(List.of(perm1, perm2))
                .build();

        role2 = Role.builder()
                .id(2L)
                .name("USER")
                .permission(List.of(perm1))
                .build();
    }


    @Test
    void findAllRoles_returnsListOfRoles() {
        List<Role> expectedRoles = List.of(role1, role2);

        Mockito.when(rolePersistancePort.findAllRoles()).thenReturn(expectedRoles);

        List<Role> result = roleService.findAllRoles();

        assertEquals(2, result.size());
        assertEquals("ADMIN", result.get(0).getName());
        assertEquals("USER", result.get(1).getName());

        Mockito.verify(rolePersistancePort).findAllRoles();
    }

    @Test
    void findAllRoles_returnsEmptyListIfNoRolesFound() {
        Mockito.when(rolePersistancePort.findAllRoles()).thenReturn(Collections.emptyList());

        List<Role> result = roleService.findAllRoles();

        assertTrue(result.isEmpty());
        Mockito.verify(rolePersistancePort).findAllRoles();
    }
}
