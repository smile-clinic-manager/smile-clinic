package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.TreatmentPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Treatment;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TreatmentServiceTest {

    @Mock
    private TreatmentPersistancePort treatmentPersistancePort;

    @InjectMocks
    private TreatmentService treatmentService;

    private final Treatment mockTreatment = Treatment.builder()
            .id(1L)
            .name("Fisioterapia")
            .notes("Terapia para rehabilitación")
            .clinicId(10L)
            .build();

    @Test
    void findById_whenTreatmentExists_returnsTreatment() {
        Mockito.when(treatmentPersistancePort.findById(1L)).thenReturn(Optional.of(mockTreatment));

        Treatment result = treatmentService.findById(1L);

        assertEquals("Fisioterapia", result.getName());
    }

    @Test
    void findById_whenTreatmentNotFound_throwsException() {
        Mockito.when(treatmentPersistancePort.findById(1L)).thenReturn(Optional.empty());

        NoSuchElementException ex = assertThrows(NoSuchElementException.class, () -> {
            treatmentService.findById(1L);
        });

        assertTrue(ex.getMessage().contains("No treatment found"));
    }

    @Test
    void findAll_returnsAllTreatments() {
        List<Treatment> treatments = List.of(mockTreatment);
        Mockito.when(treatmentPersistancePort.findAll()).thenReturn(treatments);

        List<Treatment> result = treatmentService.findAll();

        assertEquals(1, result.size());
        assertEquals("Fisioterapia", result.get(0).getName());
    }

    @Test
    void findByClinicId_setsClinicIdOnEachTreatment() {
        Treatment t1 = Treatment.builder().id(1L).clinicId(null).build();
        Treatment t2 = Treatment.builder().id(2L).clinicId(null).build();

        List<Treatment> mockList = List.of(t1, t2);
        Mockito.when(treatmentPersistancePort.findByClinicId(99L)).thenReturn(mockList);

        List<Treatment> result = treatmentService.findByClinicId(99L);

        assertEquals(2, result.size());
        assertEquals(99L, result.get(0).getClinicId());
        assertEquals(99L, result.get(1).getClinicId());
    }

    @Test
    void save_callsPersistenceSave() {
        Mockito.when(treatmentPersistancePort.save(mockTreatment)).thenReturn(mockTreatment);

        Treatment result = treatmentService.save(mockTreatment);

        assertEquals("Fisioterapia", result.getName());
    }

    @Test
    void update_callsPersistenceUpdate() {
        Mockito.when(treatmentPersistancePort.update(1L, mockTreatment)).thenReturn(mockTreatment);

        Treatment result = treatmentService.update(1L, mockTreatment);

        assertEquals(1L, result.getId());
    }

    @Test
    void delete_callsPersistenceDelete() {
        treatmentService.delete(mockTreatment);

        Mockito.verify(treatmentPersistancePort).delete(mockTreatment);
    }

    @Test
    void create_delegatesToSave() {
        Mockito.when(treatmentPersistancePort.save(mockTreatment)).thenReturn(mockTreatment);

        Treatment result = treatmentService.create(mockTreatment);

        assertEquals("Fisioterapia", result.getName());
    }
}
