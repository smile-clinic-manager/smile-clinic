package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.PreviousDiseasesServicePort;
import com.smile.clinic.smile_clinic.domain.models.PreviousDiseases;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.PreviousDiseasesMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.PreviousDiseasesDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/previous-diseases")
public class PreviousDiseaseController {
    private final PreviousDiseasesServicePort previousDiseasesServicePort;
    private final PreviousDiseasesMapper previousDiseasesMapper;

    @GetMapping("/getByMedicalHistoryId")
    public ResponseEntity<List<PreviousDiseasesDTO>> getByMedicalHistoryId(@RequestParam("medicalHistoryId") Long medicalHistoryId){
        List<PreviousDiseasesDTO> previousDiseasesDTOS = this.previousDiseasesMapper.toPreviousDiseasesDTOList(
                this.previousDiseasesServicePort.getByMedicalHistoryId(medicalHistoryId));
        return new ResponseEntity<>(previousDiseasesDTOS, HttpStatus.OK);
    }

    @GetMapping("/getAllDiseases")
    public ResponseEntity<List<PreviousDiseasesDTO>> getAllDiseases(){
        List<PreviousDiseasesDTO> previousDiseasesDTOS = this.previousDiseasesMapper.toPreviousDiseasesDTOList(
                this.previousDiseasesServicePort.getAllDiseases());
        return new ResponseEntity<>(previousDiseasesDTOS, HttpStatus.OK);
    }
}
