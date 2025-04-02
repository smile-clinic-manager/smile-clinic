package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.PatientServicePort;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.PatientRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.patientsDTO.PatientDTO;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/findPatientsByClinicId")
    public ResponseEntity<List<PatientDTO>> findByClinicId(@RequestParam("clinicId") Long clinicId){
        List<PatientDTO> patientDTO = patientRestMapper.toPatientDTOList(patientServicePort.findByClinicId(clinicId));
        return new ResponseEntity<>(patientDTO, HttpStatus.OK);
    }

    @PostMapping("/savePatient")
    public ResponseEntity<PatientDTO> save(@RequestBody PatientDTO patientDTO){
        PatientDTO savedPatientDTO = patientRestMapper.toPatientDTO(patientServicePort.save(patientRestMapper.toPatient(patientDTO)));
        return new ResponseEntity<>(savedPatientDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updatePatient")
    public ResponseEntity<PatientDTO> update(@RequestParam("id") Long id, @RequestBody PatientDTO patientDTO){
        PatientDTO updatedPatientDTO = patientRestMapper.toPatientDTO(patientServicePort.update(id, patientRestMapper.toPatient(patientDTO)));
        return new ResponseEntity<>(updatedPatientDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deletePatient")
    public ResponseEntity<Void> delete(@RequestParam("id") Long id){
        patientServicePort.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
