package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest;

import com.smile.clinic.smile_clinic.application.ports.input.TreatmentServicePort;
import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.TreatmentRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.TreatmentDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/treatments")
@RequiredArgsConstructor
public class TreatmentRestController {

    private final TreatmentServicePort treatmentServicePort;
    private final TreatmentRestMapper treatmentRestMapper;

    @GetMapping("/findAll")
    public ResponseEntity<List<TreatmentDTO>> findAll(){
        List<TreatmentDTO> treatmentsDTO = treatmentRestMapper.toTreatmentDTOList(treatmentServicePort.findAll());
        return new ResponseEntity<>(treatmentsDTO, HttpStatus.OK);
    }

    @GetMapping("/findTreatment")
    public ResponseEntity<TreatmentDTO> findTreatmentByIdentifier(@RequestParam("identifier") Long id){
        TreatmentDTO treatmentDTO = treatmentRestMapper.toTreatmentDTO(treatmentServicePort.findById(id));
        return new ResponseEntity<>(treatmentDTO, HttpStatus.OK);
    }

    @GetMapping("/deleteTreatment")
    public ResponseEntity<Void> deleteTreatmentByIdentifier(@RequestParam("identifier") Long id){
        Treatment treatment = treatmentServicePort.findById(id);
        treatmentServicePort.delete(treatment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
