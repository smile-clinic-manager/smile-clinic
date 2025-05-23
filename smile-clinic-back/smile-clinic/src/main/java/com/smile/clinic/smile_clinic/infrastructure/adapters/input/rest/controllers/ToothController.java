package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.ToothServicePort;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.ToothRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.ToothDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/tooth")
public class ToothController {
    private final ToothServicePort toothServicePort;
    private final ToothRestMapper toothRestMapper;

    @GetMapping("/findAllTeeth")
    public ResponseEntity<Object> findAllTeeth(@RequestParam("medicalHistoryId") Long medicalHistoryId) {
        try{
            List<ToothDTO> teeth = toothRestMapper.toToothDTOList(this.toothServicePort.findAllTeeth(medicalHistoryId));
            return new ResponseEntity<>(teeth, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/getToothEntities")
    public ResponseEntity<Object> getToothEntities() {
        try{
            List<ToothDTO> teeth = toothRestMapper.toToothDTOList(this.toothServicePort.getToothEntities());
            return new ResponseEntity<>(teeth, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
