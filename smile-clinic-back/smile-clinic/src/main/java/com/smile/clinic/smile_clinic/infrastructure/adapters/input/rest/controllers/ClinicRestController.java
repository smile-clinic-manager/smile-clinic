package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.ClinicServicePort;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.ClinicRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.ClinicDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/clinics")
@RequiredArgsConstructor
public class ClinicRestController {

    private final ClinicServicePort clinicServicePort;
    private final ClinicRestMapper clinicRestMapper;

    @GetMapping("/findAllByUserId")
    public ResponseEntity<List<ClinicDTO>> findAll(@RequestParam("id") Long id){
        List<ClinicDTO> clinicsDTO = clinicRestMapper.toClinicDTOList(clinicServicePort.findAllByUserId(id));
        return new ResponseEntity<>(clinicsDTO, HttpStatus.OK);
    }

    @GetMapping("/findClinicById")
    public ResponseEntity<ClinicDTO> findClinicById(@RequestParam("id") Long id){
        ClinicDTO clinicDTO = clinicRestMapper.toClinicDTO(clinicServicePort.findById(id));
        return new ResponseEntity<>(clinicDTO, HttpStatus.OK);
    }

    @GetMapping("/findClinicByAddress")
    public ResponseEntity<ClinicDTO> findClinicByAddress(@RequestParam("address") String address){
        ClinicDTO clinicDTO = clinicRestMapper.toClinicDTO(clinicServicePort.findByAddress(address));
        return new ResponseEntity<>(clinicDTO, HttpStatus.OK);
    }

    @PostMapping("/createClinic")
    public ResponseEntity<ClinicDTO> createClinic(@Valid @RequestBody ClinicDTO clinicData){
        ClinicDTO clinicDTO = clinicRestMapper.toClinicDTO(
                clinicServicePort.save(clinicRestMapper.toClinic(clinicData)));
        return new ResponseEntity<>(clinicDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateClinic")
    public ResponseEntity<ClinicDTO> updateClinic(@RequestParam("id") Long id,
                                                  @Valid @RequestBody ClinicDTO clinicData){
        ClinicDTO clinicDTO = clinicRestMapper.toClinicDTO(
                clinicServicePort.update(id, clinicRestMapper.toClinic(clinicData)));
        return new ResponseEntity<>(clinicDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteClinicById")
    public ResponseEntity<ClinicDTO> deleteClinic(@RequestParam("id") Long id){
        clinicServicePort.deleteClinicById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
