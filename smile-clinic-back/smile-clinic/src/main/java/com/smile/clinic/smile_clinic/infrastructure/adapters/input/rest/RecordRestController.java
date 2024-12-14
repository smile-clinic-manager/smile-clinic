package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest;

import com.smile.clinic.smile_clinic.application.ports.input.RecordServicePort;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.RecordRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.RecordDTO;
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

    private final RecordServicePort recordServicePort;
    private final RecordRestMapper recordRestMapper;

    @GetMapping("/findAll")
    public ResponseEntity<List<RecordDTO>> findAll(){
        List<RecordDTO> recordsDTO = recordRestMapper.toRecordDTOList(recordServicePort.findAll());
        return new ResponseEntity<>(recordsDTO, HttpStatus.OK);
    }

    @GetMapping("/findRecordById")
    public ResponseEntity<RecordDTO> findRecordById(@RequestParam("id") int id){
        RecordDTO recordDTO = recordRestMapper.toRecordDTO(recordServicePort.findById((long) id));
        return new ResponseEntity<>(recordDTO, HttpStatus.OK);
    }

    @GetMapping("/findRecordByPatientId")
    public ResponseEntity<List<RecordDTO>> findRecordByPatientId(@RequestParam("patientId") int patientId){
        List<RecordDTO> recordsDTO = recordRestMapper.toRecordDTOList(recordServicePort.findByPatientId(patientId));
        return new ResponseEntity<>(recordsDTO, HttpStatus.OK);
    }

    @GetMapping("/findRecordByUserId")
    public ResponseEntity<List<RecordDTO>> findRecordByUserId(@RequestParam("userId") int userId){
        List<RecordDTO> recordsDTO = recordRestMapper.toRecordDTOList(recordServicePort.findByUserId(userId));
        return new ResponseEntity<>(recordsDTO, HttpStatus.OK);
    }

    @GetMapping("/findRecordByTreatmentIdentifier")
    public ResponseEntity<List<RecordDTO>> findRecordByTreatmentIdentifier(@RequestParam("treatmentIdentifier") String treatmentIdentifier){
        List<RecordDTO> recordsDTO = recordRestMapper.toRecordDTOList(recordServicePort.findByTreatmentIdentifier(treatmentIdentifier));
        return new ResponseEntity<>(recordsDTO, HttpStatus.OK);
    }

    @GetMapping("/deleteRecordById")
    public ResponseEntity<Void> deleteRecordById(@RequestParam("id") int id){
        final RecordDTO recordDTO = recordRestMapper.toRecordDTO(recordServicePort.findById((long) id));
        recordServicePort.delete(recordRestMapper.toRecord(recordDTO));
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
