package com.smile.clinic.smile_clinic.application.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.smile.clinic.smile_clinic.application.ports.output.MedicalHistoryPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

class MedicalHistoryServiceTest {

    @Mock
    private MedicalHistoryPersistancePort medicalHistoryPersistancePort;

    @InjectMocks
    private MedicalHistoryService medicalHistoryService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getMedicalHistoryByPatientId_returnsMedicalHistory() {
        Long patientId = 1L;
        MedicalHistory medicalHistory = MedicalHistory.builder()
                .id(100L)
                .allergies("Pollen")
                .previousDiseases(List.of())
                .build();

        when(medicalHistoryPersistancePort.getMedicalHistoryByPatientId(patientId)).thenReturn(medicalHistory);

        MedicalHistory result = medicalHistoryService.getMedicalHistoryByPatientId(patientId);

        assertNotNull(result);
        assertEquals(100L, result.getId());
        assertEquals("Pollen", result.getAllergies());
    }

    @Test
    void updateMedicalHistory_success() {
        Long medicalHistoryId = 1L;
        MedicalHistory existing = MedicalHistory.builder()
                .id(medicalHistoryId)
                .allergies("Dust")
                .previousDiseases(List.of())
                .build();

        MedicalHistory updated = MedicalHistory.builder()
                .id(medicalHistoryId)
                .allergies("Dust, Pollen")
                .previousDiseases(List.of())
                .build();

        when(medicalHistoryPersistancePort.findById(medicalHistoryId)).thenReturn(Optional.of(existing));
        when(medicalHistoryPersistancePort.save(any(MedicalHistory.class))).thenAnswer(invocation -> invocation.getArgument(0));

        MedicalHistory result = medicalHistoryService.updateMedicalHistory(updated);

        assertNotNull(result);
        assertEquals("Dust, Pollen", result.getAllergies());
        verify(medicalHistoryPersistancePort).save(existing);
    }

    @Test
    void updateMedicalHistory_throwsWhenNotFound() {
        Long medicalHistoryId = 1L;
        MedicalHistory toUpdate = new MedicalHistory();
        toUpdate.setId(medicalHistoryId);

        when(medicalHistoryPersistancePort.findById(medicalHistoryId)).thenReturn(Optional.empty());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> medicalHistoryService.updateMedicalHistory(toUpdate));

        assertEquals("Medical history not found", ex.getMessage());
    }

    @Test
    void findMedicalHistoryById_returnsValue() {
        Long id = 10L;
        MedicalHistory medicalHistory = MedicalHistory.builder().id(id).build();

        when(medicalHistoryPersistancePort.findById(id)).thenReturn(Optional.of(medicalHistory));

        MedicalHistory result = medicalHistoryService.findMedicalHistoryById(id);

        assertNotNull(result);
        assertEquals(id, result.getId());
    }

    @Test
    void findMedicalHistoryById_throwsWhenNotFound() {
        Long id = 10L;

        when(medicalHistoryPersistancePort.findById(id)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> medicalHistoryService.findMedicalHistoryById(id));
    }

    @Test
    void bindRecordToMedicalHistory_invokesPersistence() {
        Long medicalHistoryId = 1L;
        MedicalRecordEntry record = new MedicalRecordEntry();

        medicalHistoryService.bindRecordToMedicalHistory(medicalHistoryId, record);

        verify(medicalHistoryPersistancePort).bindRecordToMedicalHistory(medicalHistoryId, record);
    }

    @Test
    void insertToothRelationship_invokesPersistence() {
        Long medicalRecordId = 1L;
        Long toothId = 2L;

        medicalHistoryService.insertToothRelationship(medicalRecordId, toothId);

        verify(medicalHistoryPersistancePort).insertToothRelationship(medicalRecordId, toothId);
    }

    @Test
    void findRelatedTeethIds_returnsList() {
        Long medicalRecordId = 1L;
        List<Long> teethIds = List.of(10L, 20L);

        when(medicalHistoryPersistancePort.findRelatedTeeth(medicalRecordId)).thenReturn(teethIds);

        List<Long> result = medicalHistoryService.findRelatedTeethIds(medicalRecordId);

        assertEquals(2, result.size());
        assertTrue(result.contains(10L));
        assertTrue(result.contains(20L));
    }
}
