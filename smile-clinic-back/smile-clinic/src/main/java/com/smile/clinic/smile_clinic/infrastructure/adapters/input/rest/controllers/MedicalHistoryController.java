package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.MedicalHistoryServicePort;
import com.smile.clinic.smile_clinic.application.ports.input.PatientServicePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.MedicalHistoryRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/medical-history")
@AllArgsConstructor
public class MedicalHistoryController {
    private final MedicalHistoryServicePort medicalHistoryServicePort;
    private final PatientServicePort patientServicePort;
    private final MedicalHistoryRestMapper medicalHistoryRestMapper;

    @GetMapping(value = "/getMedicalHistoryByPatientId")
    public ResponseEntity<MedicalHistoryDTO> getMedicalHistoryByPatientId(@RequestParam("patientId") Long patientId) {
        try {
            Patient patient = this.patientServicePort.findById(patientId);
            MedicalHistory m = this.medicalHistoryServicePort.getMedicalHistoryByPatientId(patientId);
            MedicalHistoryDTO medicalHistoryDTO = medicalHistoryRestMapper.toMedicalHistoryDTO(m);

            return new ResponseEntity<>(medicalHistoryDTO, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
