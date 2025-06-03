package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.MedicalRecordEntryPersistancePort;
import com.smile.clinic.smile_clinic.application.ports.output.ToothServicePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.domain.models.Tooth;
import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import com.smile.clinic.smile_clinic.domain.models.users.DentistData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ToothServiceTest {

    @Mock
    private ToothServicePersistancePort toothServicePersistancePort;

    @Mock
    private MedicalRecordEntryPersistancePort medicalRecordEntryPersistancePort;

    @InjectMocks
    private ToothService toothService;

    private Tooth tooth;

    @BeforeEach
    void setUp() {
        DentistData dentist = DentistData.builder()
                .id(5L)
                .firstName("John")
                .lastName1("Doe")
                .lastName2("Smith")
                .build();

        TreatmentInstance treatmentInstance = TreatmentInstance.builder()
                .id(100L)
                .name("Empaste")
                .notes("Obturación de caries")
                .price(60.0)
                .build();

        MedicalRecordEntry entry = MedicalRecordEntry.builder()
                .id(10L)
                .dateTime(LocalDateTime.of(2024, 5, 1, 10, 0))
                .observations("Caries tratada con éxito")
                .user(dentist)
                .treatmentInstance(treatmentInstance)
                .build();

        tooth = Tooth.builder()
                .id(1L)
                .code("11")
                .name("Incisivo central superior")
                .medicalRecords(List.of(entry))
                .build();
    }

    @Test
    void findAllTeeth_returnsTeethListRegardlessOfMedicalHistoryId() {
        Mockito.when(toothServicePersistancePort.findAllTeeth()).thenReturn(List.of(tooth));

        List<Tooth> result = toothService.findAllTeeth(999L);

        assertEquals(1, result.size());
        Tooth resultTooth = result.get(0);

        assertEquals("11", resultTooth.getCode());
        assertEquals("Incisivo central superior", resultTooth.getName());

        MedicalRecordEntry record = resultTooth.getMedicalRecords().get(0);
        assertEquals("Caries tratada con éxito", record.getObservations());

        DentistData dentist = record.getUser();
        assertEquals("John", dentist.getFirstName());
        assertEquals("Doe", dentist.getLastName1());
        assertEquals("Smith", dentist.getLastName2());

        TreatmentInstance treatment = record.getTreatmentInstance();
        assertEquals("Empaste", treatment.getName());
        assertEquals("Obturación de caries", treatment.getNotes());
        assertEquals(60.0, treatment.getPrice());

        Mockito.verify(toothServicePersistancePort).findAllTeeth();
    }

    @Test
    void getToothEntities_returnsStaticToothList() {
        Mockito.when(toothServicePersistancePort.getToothEntities()).thenReturn(List.of(tooth));

        List<Tooth> result = toothService.getToothEntities();

        assertEquals(1, result.size());
        assertEquals("11", result.get(0).getCode());
    }

    @Test
    void findToothById_returnsToothObject() {
        Mockito.when(toothServicePersistancePort.findToothById(1L)).thenReturn(tooth);

        Tooth result = toothService.findToothById(1L);

        assertNotNull(result);
        assertEquals("Incisivo central superior", result.getName());
        assertEquals("11", result.getCode());

        Mockito.verify(toothServicePersistancePort).findToothById(1L);
    }
}
