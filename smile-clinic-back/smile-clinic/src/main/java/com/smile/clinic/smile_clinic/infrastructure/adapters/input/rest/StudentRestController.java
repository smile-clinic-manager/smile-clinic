package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest;

import com.smile.clinic.smile_clinic.application.ports.input.StudentServicePort;
import com.smile.clinic.smile_clinic.domain.models.Student;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers.StudentRestMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.StudentDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
@RequiredArgsConstructor
public class StudentRestController {

    private final StudentServicePort studentServicePort;
    private final StudentRestMapper studentRestMapper;

    @GetMapping("/findAll")
    public ResponseEntity<List<StudentDTO>> findAll(){
        List<StudentDTO> studentsDTO = studentRestMapper.toStudentDTOList(studentServicePort.findAll());

        return new ResponseEntity<>(studentsDTO, HttpStatus.OK);
    }

    @GetMapping("/findStudentById")
    public ResponseEntity<StudentDTO> findStudentById(@RequestParam("id") Long id){
        StudentDTO studentDTO = studentRestMapper.toStudentDTO(studentServicePort.findById(id));

        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @PostMapping("/createStudent")
    public ResponseEntity<StudentDTO> createStudent(@Valid @RequestBody StudentDTO studentData){
        StudentDTO studentDTO = studentRestMapper.toStudentDTO(
                studentServicePort.save(studentRestMapper.toStudent(studentData)));

        return new ResponseEntity<>(studentDTO, HttpStatus.CREATED);
    }

    @PutMapping("/updateStudent")
    public ResponseEntity<StudentDTO> updateStudent(@RequestParam("id") Long id,
                                                    @Valid @RequestBody StudentDTO studentData){
        StudentDTO studentDTO = studentRestMapper.toStudentDTO(
                studentServicePort.update(id, studentRestMapper.toStudent(studentData)));

        return new ResponseEntity<>(studentDTO, HttpStatus.OK);
    }

    @DeleteMapping("/deleteStudentById")
    public ResponseEntity<StudentDTO> deleteStudent(@RequestParam("id") Long id){
        studentServicePort.deleteStudentById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
