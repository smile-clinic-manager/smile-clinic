package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest;

import com.smile.clinic.smile_clinic.application.ports.input.TreatmentServicePort;
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

    @GetMapping("/findTreatmentByIdentifier")
    public ResponseEntity<TreatmentDTO> findTreatmentByIdentifier(@RequestParam("identifier") String identifier){
        TreatmentDTO treatmentDTO = treatmentRestMapper.toTreatmentDTO(treatmentServicePort.findByIdentifier(identifier));
        return new ResponseEntity<>(treatmentDTO, HttpStatus.OK);
    }

    @GetMapping("/findTreatmentByName")
    public ResponseEntity<List<TreatmentDTO>> findTreatmentByName(@RequestParam("name") String name){
        List<TreatmentDTO> treatmentsDTO = treatmentRestMapper.toTreatmentDTOList(treatmentServicePort.findByName(name));
        return new ResponseEntity<>(treatmentsDTO, HttpStatus.OK);
    }

    @GetMapping("/findTreatmentByPriceBetween")
    public ResponseEntity<List<TreatmentDTO>> findTreatmentByPriceBetween(@RequestParam("minPrice") Double minPrice,
                                                                         @RequestParam("maxPrice") Double maxPrice){
        List<TreatmentDTO> treatmentsDTO = treatmentRestMapper.toTreatmentDTOList(treatmentServicePort.findByPriceBetween(minPrice, maxPrice));
        return new ResponseEntity<>(treatmentsDTO, HttpStatus.OK);
    }

    @GetMapping("/findTreatmentIfDescriptionContains")
    public ResponseEntity<List<TreatmentDTO>> findTreatmentIfDescriptionContains(@RequestParam("substring") String substring){
        List<TreatmentDTO> treatmentsDTO = treatmentRestMapper.toTreatmentDTOList(treatmentServicePort.findIfDescriptionContains(substring));
        return new ResponseEntity<>(treatmentsDTO, HttpStatus.OK);
    }

    @GetMapping("/deleteTreatmentByIdentifier")
    public ResponseEntity<Void> deleteTreatmentByIdentifier(@RequestParam("identifier") String identifier){
        treatmentServicePort.deleteTreatmentByIdentifier(identifier);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
