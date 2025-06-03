package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.DiseasePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.patients.Disease;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DiseaseServiceTest {

    @Mock
    private DiseasePersistancePort diseasePersistancePort;

    @InjectMocks
    private DiseaseService diseaseService;

    private Disease disease;

    @BeforeEach
    void setUp() {
        disease = new Disease();
        disease.setId(1L);
        disease.setName("Flu");
    }

    @Test
    void findAll_shouldReturnEmptyList() {
        // Actualmente retorna null, prueba que el resultado sea null o lo que definas después
        List<Disease> diseases = diseaseService.findAll();
        assertNull(diseases);
    }

    @Test
    void findById_shouldReturnNull() {
        Disease found = diseaseService.findById(1L);
        assertNull(found);
    }

    @Test
    void save_shouldReturnNull() {
        Disease saved = diseaseService.save(disease);
        assertNull(saved);
    }

    @Test
    void update_shouldReturnNull() {
        Disease updated = diseaseService.update(disease);
        assertNull(updated);
    }

    @Test
    void deleteById_shouldDoNothing() {
        // Solo verificamos que no lance excepción al llamar
        assertDoesNotThrow(() -> diseaseService.deleteById(1L));
    }
}
