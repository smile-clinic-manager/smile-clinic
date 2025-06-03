package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.PreviousDiseasesPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;
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
class PreviousDiseasesServiceTest {

    @Mock
    private PreviousDiseasesPersistancePort previousDiseasesPersistancePort;

    @InjectMocks
    private PreviousDiseasesService previousDiseasesService;

    private PreviousDiseases disease1;
    private PreviousDiseases disease2;

    @BeforeEach
    void setUp() {
        disease1 = PreviousDiseases.builder()
                .id(1L)
                .name("Hipertensión")
                .build();

        disease2 = PreviousDiseases.builder()
                .id(2L)
                .name("Diabetes")
                .build();
    }

    @Test
    void getByMedicalHistoryId_returnsDiseasesList() {
        Mockito.when(previousDiseasesPersistancePort.getByMedicalHistoryId(100L))
                .thenReturn(List.of(disease1, disease2));

        List<PreviousDiseases> result = previousDiseasesService.getByMedicalHistoryId(100L);

        assertEquals(2, result.size());
        assertEquals("Hipertensión", result.get(0).getName());
        assertEquals("Diabetes", result.get(1).getName());

        Mockito.verify(previousDiseasesPersistancePort).getByMedicalHistoryId(100L);
    }

    @Test
    void getByMedicalHistoryId_returnsEmptyListIfNoData() {
        Mockito.when(previousDiseasesPersistancePort.getByMedicalHistoryId(999L))
                .thenReturn(Collections.emptyList());

        List<PreviousDiseases> result = previousDiseasesService.getByMedicalHistoryId(999L);

        assertTrue(result.isEmpty());
        Mockito.verify(previousDiseasesPersistancePort).getByMedicalHistoryId(999L);
    }

    @Test
    void getAllDiseases_returnsAllDiseases() {
        Mockito.when(previousDiseasesPersistancePort.getAllDiseases())
                .thenReturn(List.of(disease1, disease2));

        List<PreviousDiseases> result = previousDiseasesService.getAllDiseases();

        assertEquals(2, result.size());
        assertEquals("Hipertensión", result.get(0).getName());
        assertEquals("Diabetes", result.get(1).getName());

        Mockito.verify(previousDiseasesPersistancePort).getAllDiseases();
    }

    @Test
    void getAllDiseases_returnsEmptyList() {
        Mockito.when(previousDiseasesPersistancePort.getAllDiseases())
                .thenReturn(Collections.emptyList());

        List<PreviousDiseases> result = previousDiseasesService.getAllDiseases();

        assertTrue(result.isEmpty());
        Mockito.verify(previousDiseasesPersistancePort).getAllDiseases();
    }
}
