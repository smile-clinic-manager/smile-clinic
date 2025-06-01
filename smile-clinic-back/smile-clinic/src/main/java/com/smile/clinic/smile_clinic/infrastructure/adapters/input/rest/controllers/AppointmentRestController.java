package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.controllers;

import com.smile.clinic.smile_clinic.application.ports.input.AppointmentServicePort;
import com.smile.clinic.smile_clinic.application.services.UserService;
import com.smile.clinic.smile_clinic.domain.exceptions.AppointmentNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.patients.Patient;
import com.smile.clinic.smile_clinic.domain.models.users.DentistData;
import com.smile.clinic.smile_clinic.domain.models.users.User;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.AppointmentRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.AppointmentDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.AppointmentFormDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.DentistDataDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.ErrorResponseDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.patientsDTO.PatientDTO;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.UserPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.UserEntityRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Slf4j
@RestController
@RequestMapping("/appointments")
@RequiredArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class AppointmentRestController {

    private final AppointmentServicePort appointmentServicePort;
    private final AppointmentRestMapper mapper;

    private final UserEntityRepository userEntityRepository;
    private final UserPersistanceMapper userPersistanceMapper;
    private final PatientRestController patientRestController;

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

    @GetMapping("/findByClinicId")
    public ResponseEntity<Object> findAppointmentByClinicId(@RequestParam("clinicId") Long clinicId){
        try{
            List<AppointmentDTO> appointment = mapper.toAppointmentDTOList(this.appointmentServicePort.findByClinicId(clinicId));
            return new ResponseEntity<>(appointment, HttpStatus.OK);
        } catch (Exception e){
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

    @PostMapping("/createAppointment")
    public ResponseEntity<Object> createAppointment(@RequestBody AppointmentFormDTO appointmentFormDTO){
        try{
            AppointmentDTO appointmentDTO = mapper.toAppointmentDTO(
                    appointmentServicePort.save(mapper.toAppointment(toAppointmentDTO(appointmentFormDTO))));
            return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
        } catch (Exception e){
            ErrorResponseDTO response = new ErrorResponseDTO(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/updateAppointment")
    public ResponseEntity<Object> updateAppointment(@RequestBody AppointmentFormDTO appointmentFormDTO,
                                                    @RequestParam("id") Long id){
        try{
            AppointmentDTO appointmentDTO = mapper.toAppointmentDTO(
                    appointmentServicePort.update(mapper.toAppointment(toAppointmentDTO(appointmentFormDTO)), id));
            return new ResponseEntity<>(appointmentDTO, HttpStatus.OK);
        } catch (Exception e){
            ErrorResponseDTO response = new ErrorResponseDTO(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/deleteAppointment")
    public ResponseEntity<Object> deleteAppointment(@RequestParam("appointmentId") Long appointmentId){
        try{
            this.appointmentServicePort.delete(appointmentId);
            return new ResponseEntity<>(appointmentId, HttpStatus.OK);
        } catch (Exception e){
            ErrorResponseDTO response = new ErrorResponseDTO(e.getMessage());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    private AppointmentDTO toAppointmentDTO(AppointmentFormDTO appointmentFormDTO) {

        DentistDataDTO dentistDataDTO = toDentistDataDTO(
                userPersistanceMapper.toUser(userEntityRepository.findById(appointmentFormDTO.getUserId())
                        .orElseThrow(() -> new RuntimeException("User not found"))));
        PatientDTO patientDTO = patientRestController.findById(appointmentFormDTO.getPatientId()).getBody();

        return AppointmentDTO.builder()
                .id(appointmentFormDTO.getId())
                .duration(appointmentFormDTO.getDuration())
                .visitPurpose(appointmentFormDTO.getVisitPurpose())
                .dateTime(LocalDateTime.parse(appointmentFormDTO.getDate() + "T" + appointmentFormDTO.getTime()))
                .user(dentistDataDTO)
                .patient(patientDTO)
                .build();
    }

    private DentistDataDTO toDentistDataDTO(User user) {

        return DentistDataDTO.builder()
                .id(user.getId())
                .firstName(user.getFirstName())
                .lastName1(user.getLastName1())
                .lastName2(user.getLastName2())
                .build();
    }

}