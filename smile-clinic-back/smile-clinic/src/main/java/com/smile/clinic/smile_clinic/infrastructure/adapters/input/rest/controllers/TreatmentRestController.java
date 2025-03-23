package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.TreatmentServicePort;
import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.TreatmentRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.ErrorResponseDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.TreatmentDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

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

    @GetMapping("/findTreatmentById")
    public ResponseEntity<TreatmentDTO> findTreatmentById(@RequestParam("id") Long id){
        TreatmentDTO treatmentDTO = treatmentRestMapper.toTreatmentDTO(treatmentServicePort.findById(id));
        return new ResponseEntity<>(treatmentDTO, HttpStatus.OK);
    }

    @GetMapping("/findTreatmentsByClinicId")
    public ResponseEntity<List<TreatmentDTO>> findTreatmentByClinicId(@RequestParam("clinicId") Long id){
        List<TreatmentDTO> treatmentDTOs = treatmentRestMapper.toTreatmentDTOList(treatmentServicePort.findByClinicId(id));
        return new ResponseEntity<>(treatmentDTOs, HttpStatus.OK);
    }

    @GetMapping("/deleteTreatment")
    public ResponseEntity<Void> deleteTreatmentById(@RequestParam("treatmentId") Long id){
        Treatment treatment = treatmentServicePort.findById(id);
        if(treatment == null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        try{
            treatmentServicePort.delete(treatment);
        }catch (Exception exception){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createTreatment(@RequestBody TreatmentDTO treatmentDTO){
        if (treatmentDTO.getId() != null) {
            Treatment treatment = treatmentServicePort.findById(treatmentDTO.getId());
            if (treatment != null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(new ErrorResponseDTO("El tratamiento ya existe"));
            }
        }
        try{
            TreatmentDTO responseTreatment = treatmentRestMapper.toTreatmentDTO(
                    treatmentServicePort.create(treatmentRestMapper.toTreatment(treatmentDTO)));
            return new ResponseEntity<>(responseTreatment, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
