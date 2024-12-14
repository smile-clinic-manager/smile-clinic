package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest;

import com.smile.clinic.smile_clinic.application.ports.input.MedicalRecordEntryServicePort;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.MedicalRecordEntryRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.MedicalRecordEntryDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/records")
@RequiredArgsConstructor
public class RecordRestController {

    private final MedicalRecordEntryServicePort medicalRecordEntryServicePort;
    private final MedicalRecordEntryRestMapper medicalRecordEntryRestMapper;

    @GetMapping("/findAll")
    public ResponseEntity<List<MedicalRecordEntryDTO>> findAll(){
        List<MedicalRecordEntryDTO> recordsDTO = medicalRecordEntryRestMapper.toMedicalRecordEntryDTOList(medicalRecordEntryServicePort.findAll());
        return new ResponseEntity<>(recordsDTO, HttpStatus.OK);
    }

    @GetMapping("/findRecordById")
    public ResponseEntity<MedicalRecordEntryDTO> findRecordById(@RequestParam("id") Long id){
        MedicalRecordEntryDTO medicalRecordEntryDTO = medicalRecordEntryRestMapper.toMedicalRecordEntryDTO(medicalRecordEntryServicePort.findById((id)));
        return new ResponseEntity<>(medicalRecordEntryDTO, HttpStatus.OK);
    }


    @GetMapping("/deleteRecordById")
    public ResponseEntity<Void> deleteRecordById(@RequestParam("id") Long id){
        final MedicalRecordEntryDTO medicalRecordEntryDTO = medicalRecordEntryRestMapper.toMedicalRecordEntryDTO(medicalRecordEntryServicePort.findById((id)));
        medicalRecordEntryServicePort.delete(medicalRecordEntryRestMapper.toMedicalRecordEntry(medicalRecordEntryDTO));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
