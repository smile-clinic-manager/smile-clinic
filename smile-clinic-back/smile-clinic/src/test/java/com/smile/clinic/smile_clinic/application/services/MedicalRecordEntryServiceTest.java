package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.MedicalRecordEntryPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalRecordEntryFormDTO;
import com.smile.clinic.smile_clinic.utils.DateTimeComposer;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class MedicalRecordEntryServiceTest {

    @Mock
    private MedicalRecordEntryPersistancePort medicalRecordEntryPersistancePort;

    @Mock
    private TreatmentInstanceService treatmentInstanceService;

    @Mock
    private UserService userService;

    @Mock
    private MedicalHistoryService medicalHistoryService;

    @Mock
    private DateTimeComposer dateTimeComposer;

    @InjectMocks
    private MedicalRecordEntryService medicalRecordEntryService;

    private MedicalRecordEntryFormDTO medicalRecordEntryForm;

    @BeforeEach
    void setUp() {
        medicalRecordEntryForm = new MedicalRecordEntryFormDTO();
        medicalRecordEntryForm.setDate("2025-06-03");
        medicalRecordEntryForm.setTime("10:00");
        medicalRecordEntryForm.setTreatmentId(1L);
        medicalRecordEntryForm.setMedicalHistoryId(1L);
        medicalRecordEntryForm.setUserId(1L);
        medicalRecordEntryForm.setObservations("Observations");
        medicalRecordEntryForm.setTeethListId(List.of("123", "456"));
    }

    @Test
    void createMedicalRecordEntry_success() {
        TreatmentInstance treatmentInstance = new TreatmentInstance();
        User user = new User();
        MedicalRecordEntry savedRecord = new MedicalRecordEntry();
        savedRecord.setId(1L);

        when(treatmentInstanceService.createInstanceFromTreatmentDTO(anyLong()))
                .thenReturn(treatmentInstance);

        when(userService.findUserByUserId(anyLong())).thenReturn(user);

        when(dateTimeComposer.composeDateTime(anyString(), anyString()))
                .thenReturn(LocalDateTime.now());

        when(medicalRecordEntryPersistancePort.createMedicalRecordEntry(any(MedicalRecordEntry.class), any(User.class)))
                .thenReturn(savedRecord);

        // CORRECCIÓN: Usar matchers para TODOS los argumentos en insertToothRelationship
        doNothing().when(medicalHistoryService).insertToothRelationship(anyLong(), anyLong());

        doNothing().when(medicalHistoryService).bindRecordToMedicalHistory(anyLong(), any(MedicalRecordEntry.class));

        MedicalRecordEntry result = medicalRecordEntryService.createMedicalRecordEntry(medicalRecordEntryForm);

        assertNotNull(result);
        assertEquals(savedRecord.getId(), result.getId());

        verify(medicalHistoryService, times(2))
                .insertToothRelationship(eq(savedRecord.getId()), anyLong());

        verify(medicalHistoryService).bindRecordToMedicalHistory(eq(medicalRecordEntryForm.getMedicalHistoryId()), eq(savedRecord));
    }

    @Test
    void editMedicalRecordEntry_success() {
        MedicalRecordEntry existingRecord = new MedicalRecordEntry();
        existingRecord.setId(medicalRecordEntryForm.getMedicalRecordId());
        TreatmentInstance newTreatmentInstance = new TreatmentInstance();

        medicalRecordEntryForm.setMedicalRecordId(1L);

        when(medicalRecordEntryPersistancePort.findById(anyLong())).thenReturn(existingRecord);
        when(treatmentInstanceService.createInstanceFromTreatmentDTO(anyLong())).thenReturn(newTreatmentInstance);
        when(dateTimeComposer.composeDateTime(anyString(), anyString())).thenReturn(LocalDateTime.now());
        when(medicalRecordEntryPersistancePort.update(any(MedicalRecordEntry.class))).thenReturn(existingRecord);

        doNothing().when(medicalRecordEntryPersistancePort).clearToothRelationships(anyLong());
        doNothing().when(medicalHistoryService).insertToothRelationship(anyLong(), anyLong());

        MedicalRecordEntry updated = medicalRecordEntryService.editMedicalRecordEntry(medicalRecordEntryForm);

        assertNotNull(updated);

        verify(medicalRecordEntryPersistancePort).clearToothRelationships(eq(medicalRecordEntryForm.getMedicalRecordId()));
        verify(medicalHistoryService, times(medicalRecordEntryForm.getTeethListId().size()))
                .insertToothRelationship(eq(medicalRecordEntryForm.getMedicalRecordId()), anyLong());
    }

    @Test
    void deleteById_success() {
        MedicalRecordEntry record = new MedicalRecordEntry();
        record.setId(1L);

        when(medicalRecordEntryPersistancePort.findById(anyLong())).thenReturn(record);
        doNothing().when(medicalRecordEntryPersistancePort).clearToothRelationships(anyLong());
        doNothing().when(medicalRecordEntryPersistancePort).delete(any(MedicalRecordEntry.class));

        medicalRecordEntryService.deleteById(1L);

        verify(medicalRecordEntryPersistancePort).clearToothRelationships(eq(1L));
        verify(medicalRecordEntryPersistancePort).delete(eq(record));
    }

    @Test
    void deleteById_notFound_shouldThrow() {
        when(medicalRecordEntryPersistancePort.findById(anyLong())).thenReturn(null);

        assertThrows(EntityNotFoundException.class, () -> medicalRecordEntryService.deleteById(1L));
    }
}
