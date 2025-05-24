package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.MedicalRecordEntryServicePort;
import com.smile.clinic.smile_clinic.domain.models.MedicalHistory;
import com.smile.clinic.smile_clinic.domain.models.MedicalRecordEntry;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.MedicalRecordEntryRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalHistoryDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalRecordEntryDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalRecordEntryFormDTO;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/medical-records")
@AllArgsConstructor
public class MedicalRecordEntryController {
    private final MedicalRecordEntryRestMapper medicalRecordEntryRestMapper;
    private final MedicalRecordEntryServicePort medicalRecordEntryServicePort;

    @GetMapping(value = "/getAllMedicalRecordsByMedicalHistory")
    public ResponseEntity<List<MedicalRecordEntryDTO>> getMedicalHistoryByPatientId(@RequestParam("medicalHistoryId") Long medicalHistoryId) {
        try {
            List<MedicalRecordEntry> medicalRecords = this.medicalRecordEntryServicePort.findAllByMedicalHistory(medicalHistoryId);
            List<MedicalRecordEntryDTO> medicalRecordsDTO = this.medicalRecordEntryRestMapper.toMedicalRecordEntryDTOList(medicalRecords);

            return new ResponseEntity<>(medicalRecordsDTO, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/createMedicalRecordEntry")
    public ResponseEntity<List<MedicalRecordEntryDTO>> createMedicalRecordEntry(@RequestBody MedicalRecordEntryFormDTO medicalRecordEntryForm) {
        try {
            MedicalRecordEntry medicalRecordEntry = this.medicalRecordEntryServicePort.createMedicalRecordEntry(medicalRecordEntryForm);
            medicalRecordEntryRestMapper.toMedicalRecordEntryDTO(medicalRecordEntry);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
