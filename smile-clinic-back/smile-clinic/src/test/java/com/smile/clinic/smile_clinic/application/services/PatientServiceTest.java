package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.output.PatientPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientPersistancePort patientPersistancePort;

    @Mock
    private MedicalHistoryService medicalHistoryService;

    @InjectMocks
    private PatientService patientService;

    private Patient patient;
    private MedicalHistory medicalHistory;

    @BeforeEach
    void setUp() {
        medicalHistory = MedicalHistory.builder()
                .id(1L)
                .allergies("Peanuts")
                .previousDiseases(List.of(
                        new PreviousDiseases(1L, "Diabetes"),
                        new PreviousDiseases(2L, "Hypertension")
                ))
                .build();

        patient = Patient.builder()
                .id(1L)
                .firstName("John")
                .lastName1("Doe")
                .lastName2("Smith")
                .dni("12345678A")
                .email("john.doe@example.com")
                .phoneNumber("123456789")
                .medicalHistory(medicalHistory)
                .build();
    }

    @Test
    void update_patient_success() {
        MedicalHistory updatedHistory = MedicalHistory.builder()
                .id(1L)
                .allergies("Peanuts, Pollen")
                .previousDiseases(List.of(
                        new PreviousDiseases(1L, "Diabetes")
                ))
                .build();

        Patient updatedPatient = Patient.builder()
                .id(1L)
                .firstName("Johnathan")
                .lastName1("Doe")
                .lastName2("Smith")
                .dni("12345678A")
                .email("johnathan.doe@example.com")
                .phoneNumber("987654321")
                .medicalHistory(updatedHistory)
                .build();

        // Aquí usamos any() para evitar el problema de strict stubbing
        Mockito.when(medicalHistoryService.updateMedicalHistory(Mockito.any(MedicalHistory.class)))
                .thenReturn(updatedHistory);

        Mockito.when(patientPersistancePort.findById(patient.getId()))
                .thenReturn(Optional.of(patient));

        Mockito.when(patientPersistancePort.save(Mockito.any(Patient.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Patient result = patientService.update(patient.getId(), updatedPatient);

        assertNotNull(result);
        assertEquals("Johnathan", result.getFirstName());
        assertEquals("johnathan.doe@example.com", result.getEmail());
        assertEquals(updatedHistory, result.getMedicalHistory());

        Mockito.verify(medicalHistoryService).updateMedicalHistory(Mockito.any(MedicalHistory.class));
        Mockito.verify(patientPersistancePort).save(Mockito.any(Patient.class));
    }
}
