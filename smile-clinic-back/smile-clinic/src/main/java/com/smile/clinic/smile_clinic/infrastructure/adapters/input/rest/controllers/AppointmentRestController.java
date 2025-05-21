package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.AppointmentServicePort;
import com.smile.clinic.smile_clinic.domain.exceptions.AppointmentNotFoundException;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.AppointmentRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.AppointmentDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.ErrorResponseDTO;
import lombok.AllArgsConstructor;
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
@RequestMapping("/appointments")
@AllArgsConstructor
public class AppointmentRestController {

    private final AppointmentServicePort appointmentServicePort;
    private final AppointmentRestMapper mapper;

    @GetMapping("/findById")
    public ResponseEntity<Object> findAppointmentById(@RequestParam("id") Long id){
        try{
            AppointmentDTO appointment = mapper.toAppointmentDTO(this.appointmentServicePort.findById(id));
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (AppointmentNotFoundException e){
            ErrorResponseDTO response = new ErrorResponseDTO(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByUserId")
    public ResponseEntity<Object> findAppointmentByUserId(@RequestParam("userId") Long userId){
        try{
            List<AppointmentDTO> appointment = mapper.toAppointmentDTOList(this.appointmentServicePort.findByUserId(userId));
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (Exception e){
            ErrorResponseDTO response = new ErrorResponseDTO(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/findByPatientId")
    public ResponseEntity<Object> findAppointmentByPatientId(@RequestParam("patientId") Long patientId){
        try{
            List<AppointmentDTO> appointment = mapper.toAppointmentDTOList(this.appointmentServicePort.findByPatientId(patientId));
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (Exception e){
            ErrorResponseDTO response = new ErrorResponseDTO(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
        }
    }

}
