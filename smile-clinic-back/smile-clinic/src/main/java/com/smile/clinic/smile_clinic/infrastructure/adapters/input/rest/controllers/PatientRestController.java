package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.PatientServicePort;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.PatientRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.patientsDTO.PatientDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/patients")
@RequiredArgsConstructor
public class PatientRestController {

    private final PatientServicePort patientServicePort;
    private final PatientRestMapper patientRestMapper;

    @GetMapping("/findAllPatients")
    public ResponseEntity<List<PatientDTO>> findAll(){
        List<PatientDTO> patientsDTO = patientRestMapper.toPatientDTOList(patientServicePort.findAll());
        return new ResponseEntity<>(patientsDTO, HttpStatus.OK);
    }

    @GetMapping("/findPatientById")
    public ResponseEntity<PatientDTO> findById(@RequestParam("id") Long id){
        PatientDTO patientDTO = patientRestMapper.toPatientDTO(patientServicePort.findById(id));
        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }
}
