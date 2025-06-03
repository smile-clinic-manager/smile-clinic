package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.ClinicPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.ClinicNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.Clinic;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ClinicServiceTest {

    @Mock
    private ClinicPersistancePort clinicPersistancePort;

    @InjectMocks
    private ClinicService clinicService;

    private Clinic clinic;

    @BeforeEach
    void setUp() {
        clinic = Clinic.builder()
                .id(1L)
                .name("Smile Clinic")
                .address("123 Main St")
                .postalCode("12345")
                .phoneNumber("555-1234")
                .email("contact@smileclinic.com")
                .build();
    }

    @Test
    void findAll_shouldReturnListOfClinics() {
        List<Clinic> clinics = List.of(clinic);
        when(clinicPersistancePort.findAll()).thenReturn(clinics);

        List<Clinic> result = clinicService.findAll();

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(clinicPersistancePort).findAll();
    }

    @Test
    void findAllByUserId_shouldReturnClinics() {
        List<Clinic> clinics = List.of(clinic);
        when(clinicPersistancePort.findAllByUserId(1L)).thenReturn(clinics);

        List<Clinic> result = clinicService.findAllByUserId(1L);

        assertNotNull(result);
        assertEquals(1, result.size());
        verify(clinicPersistancePort).findAllByUserId(1L);
    }

    @Test
    void findById_existingId_shouldReturnClinic() {
        when(clinicPersistancePort.findById(1L)).thenReturn(Optional.of(clinic));

        Clinic result = clinicService.findById(1L);

        assertNotNull(result);
        assertEquals(clinic.getName(), result.getName());
        verify(clinicPersistancePort).findById(1L);
    }

    @Test
    void findById_nonExistingId_shouldThrowException() {
        when(clinicPersistancePort.findById(1L)).thenReturn(Optional.empty());

        ClinicNotFoundException ex = assertThrows(ClinicNotFoundException.class,
                () -> clinicService.findById(1L));

        assertEquals("Clinic with id 1 not found", ex.getMessage());
        verify(clinicPersistancePort).findById(1L);
    }

    @Test
    void findByAddress_existingAddress_shouldReturnClinic() {
        when(clinicPersistancePort.findByAddress("123 Main St")).thenReturn(Optional.of(clinic));

        Clinic result = clinicService.findByAddress("123 Main St");

        assertNotNull(result);
        assertEquals(clinic.getName(), result.getName());
        verify(clinicPersistancePort).findByAddress("123 Main St");
    }

    @Test
    void findByAddress_nonExistingAddress_shouldThrowException() {
        when(clinicPersistancePort.findByAddress("123 Main St")).thenReturn(Optional.empty());

        ClinicNotFoundException ex = assertThrows(ClinicNotFoundException.class,
                () -> clinicService.findByAddress("123 Main St"));

        assertEquals("Clinic with address 123 Main St not found", ex.getMessage());
        verify(clinicPersistancePort).findByAddress("123 Main St");
    }

    @Test
    void save_shouldReturnSavedClinic() {
        when(clinicPersistancePort.save(clinic)).thenReturn(clinic);

        Clinic result = clinicService.save(clinic);

        assertNotNull(result);
        assertEquals(clinic.getName(), result.getName());
        verify(clinicPersistancePort).save(clinic);
    }

    @Test
    void update_existingClinic_shouldReturnUpdatedClinic() {
        Clinic updatedClinic = Clinic.builder()
                .name("New Name")
                .address("New Address")
                .postalCode("54321")
                .phoneNumber("555-4321")
                .email("newemail@clinic.com")
                .build();

        when(clinicPersistancePort.findById(1L)).thenReturn(Optional.of(clinic));
        when(clinicPersistancePort.save(any(Clinic.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Clinic result = clinicService.update(1L, updatedClinic);

        assertNotNull(result);
        assertEquals("New Name", result.getName());
        assertEquals("New Address", result.getAddress());
        assertEquals("54321", result.getPostalCode());
        assertEquals("555-4321", result.getPhoneNumber());
        assertEquals("newemail@clinic.com", result.getEmail());

        verify(clinicPersistancePort).findById(1L);
        verify(clinicPersistancePort).save(any(Clinic.class));
    }

    @Test
    void update_nonExistingClinic_shouldThrowException() {
        Clinic updatedClinic = Clinic.builder().build();
        when(clinicPersistancePort.findById(1L)).thenReturn(Optional.empty());

        ClinicNotFoundException ex = assertThrows(ClinicNotFoundException.class,
                () -> clinicService.update(1L, updatedClinic));

        assertEquals("Clinic with id 1 not found", ex.getMessage());
        verify(clinicPersistancePort).findById(1L);
        verify(clinicPersistancePort, never()).save(any());
    }

    @Test
    void deleteClinicById_existingClinic_shouldDeleteClinic() {
        when(clinicPersistancePort.findById(1L)).thenReturn(Optional.of(clinic));
        doNothing().when(clinicPersistancePort).deleteRolesByClinicId(1L);
        doNothing().when(clinicPersistancePort).deleteById(1L);

        clinicService.deleteClinicById(1L);

        verify(clinicPersistancePort).findById(1L);
        verify(clinicPersistancePort).deleteRolesByClinicId(1L);
        verify(clinicPersistancePort).deleteById(1L);
    }

    @Test
    void deleteClinicById_nonExistingClinic_shouldThrowException() {
        when(clinicPersistancePort.findById(1L)).thenReturn(Optional.empty());

        ClinicNotFoundException ex = assertThrows(ClinicNotFoundException.class,
                () -> clinicService.deleteClinicById(1L));

        assertEquals("Clinic with id 1 not found", ex.getMessage());
        verify(clinicPersistancePort).findById(1L);
        verify(clinicPersistancePort, never()).deleteRolesByClinicId(anyLong());
        verify(clinicPersistancePort, never()).deleteById(anyLong());
    }
}
