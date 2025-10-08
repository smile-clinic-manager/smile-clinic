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

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

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
            medicalRecordsDTO = medicalRecordsDTO.stream().sorted(Comparator.comparing(MedicalRecordEntryDTO::getDateTime).reversed()).collect(Collectors.toList());
            return new ResponseEntity<>(medicalRecordsDTO, HttpStatus.OK);

        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping(value = "/getAllRelatedTeeth")
    public ResponseEntity<List<Long>> getAllRelatedTeeth(@RequestParam("medicalRecordId") Long medicalRecordId) {
        try {
            List<Long> relatedTeethIds = this.medicalRecordEntryServicePort.getAllRelatedTeethIds(medicalRecordId);
            return new ResponseEntity<>(relatedTeethIds, HttpStatus.OK);

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

    @PutMapping("/editMedicalRecordEntry")
    public ResponseEntity<Object> editMedicalRecordEntry(@RequestBody MedicalRecordEntryFormDTO medicalRecordEntryForm) {
        try {
            MedicalRecordEntry medicalRecordEntry = this.medicalRecordEntryServicePort.editMedicalRecordEntry(medicalRecordEntryForm);
            medicalRecordEntryRestMapper.toMedicalRecordEntryDTO(medicalRecordEntry);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/deleteMedicalRecordEntry")
    public ResponseEntity<Object> deleteMedicalRecordEntry(@RequestParam("medicalRecordId") Long medicalRecordId) {
        try {
            this.medicalRecordEntryServicePort.deleteById(medicalRecordId);
            return ResponseEntity.status(HttpStatus.OK).build();
        } catch (EntityNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
