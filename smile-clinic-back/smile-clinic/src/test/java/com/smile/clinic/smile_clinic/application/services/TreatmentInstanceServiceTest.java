package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.TreatmentInstancePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;

@ExtendWith(MockitoExtension.class)
class TreatmentInstanceServiceTest {

    @Mock
    private TreatmentInstancePersistancePort treatmentInstancePersistancePort;

    @InjectMocks
    private TreatmentInstanceService treatmentInstanceService;

    private TreatmentInstance instance;
    private Treatment treatment;

    @BeforeEach
    void setup() {
        instance = TreatmentInstance.builder()
                .id(1L)
                .name("Masaje terapéutico")
                .notes("Aplicar en zona lumbar")
                .price(60.0)
                .build();

        treatment = Treatment.builder()
                .id(2L)
                .name("Electroterapia")
                .notes("20 minutos intensidad media")
                .price(80.0)
                .build();
    }

    @Test
    void findById_returnsInstance() {
        Mockito.when(treatmentInstancePersistancePort.findById(1L)).thenReturn(instance);

        TreatmentInstance result = treatmentInstanceService.findById(1L);

        assertEquals("Masaje terapéutico", result.getName());
        Mockito.verify(treatmentInstancePersistancePort).findById(1L);
    }

    @Test
    void findAll_returnsAllInstances() {
        Mockito.when(treatmentInstancePersistancePort.findAll()).thenReturn(List.of(instance));

        List<TreatmentInstance> result = treatmentInstanceService.findAll();

        assertEquals(1, result.size());
        assertEquals("Masaje terapéutico", result.get(0).getName());
    }

    @Test
    void findByPatientId_returnsInstancesForPatient() {
        Mockito.when(treatmentInstancePersistancePort.findByPatientId(99L)).thenReturn(List.of(instance));

        List<TreatmentInstance> result = treatmentInstanceService.findByPatientId(99L);

        assertEquals(1, result.size());
        Mockito.verify(treatmentInstancePersistancePort).findByPatientId(99L);
    }

    @Test
    void save_callsPersistenceLayer() {
        Mockito.when(treatmentInstancePersistancePort.save(instance)).thenReturn(instance);

        TreatmentInstance result = treatmentInstanceService.save(instance);

        assertEquals(60.0, result.getPrice());
        Mockito.verify(treatmentInstancePersistancePort).save(instance);
    }

    @Test
    void update_callsPersistenceUpdate() {
        Mockito.when(treatmentInstancePersistancePort.update(1L, instance)).thenReturn(instance);

        TreatmentInstance result = treatmentInstanceService.update(1L, instance);

        assertEquals("Masaje terapéutico", result.getName());
        Mockito.verify(treatmentInstancePersistancePort).update(1L, instance);
    }

    @Test
    void delete_callsPersistenceDelete() {
        treatmentInstanceService.delete(instance);

        Mockito.verify(treatmentInstancePersistancePort).delete(instance);
    }

    @Test
    void createInstanceFromTreatmentDTO_copiesFieldsAndSavesInstance() {
        Mockito.when(treatmentInstancePersistancePort.findTreatmentToCopy(2L)).thenReturn(treatment);

        ArgumentCaptor<TreatmentInstance> captor = ArgumentCaptor.forClass(TreatmentInstance.class);

        Mockito.when(treatmentInstancePersistancePort.save(any())).thenReturn(instance);

        TreatmentInstance result = treatmentInstanceService.createInstanceFromTreatmentDTO(2L);

        Mockito.verify(treatmentInstancePersistancePort).save(captor.capture());
        TreatmentInstance captured = captor.getValue();

        assertEquals("Electroterapia", captured.getName());
        assertEquals("20 minutos intensidad media", captured.getNotes());
        assertEquals(80.0, captured.getPrice());
        assertEquals("Masaje terapéutico", result.getName()); // devuelve instancia persistida mockeada
    }
}
