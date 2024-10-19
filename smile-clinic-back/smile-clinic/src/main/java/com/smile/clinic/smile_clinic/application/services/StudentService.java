package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.StudentServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.StudentPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.ExistingStudentException;
import com.smile.clinic.smile_clinic.domain.exceptions.StudentNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.Student;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

/*
*  Los services: IMPLEMENTAR los puertos de ENTRADA y UTILIZAN los puertos de SALIDA
*/

@Service
@RequiredArgsConstructor
public class StudentService implements StudentServicePort {

    private final StudentPersistancePort studentPersistancePort;

    @Override
    public Student findById(Long id) throws StudentNotFoundException {
        return this.studentPersistancePort.findById(id)
                .orElseThrow(()-> new StudentNotFoundException("Student with id "+id+" not found"));
    }

    @Override
    public Student findByFirstName(String firstName) {
        return this.studentPersistancePort.findByFirstName(firstName)
                .orElseThrow(()-> new StudentNotFoundException("Student with first name "+firstName+" not found"));

    }

    @Override
    public List<Student> findAll() {
        return this.studentPersistancePort.findAll();
    }

    @Override
    public Student save(Student student) throws ExistingStudentException {
        boolean alreadyExists = this.studentPersistancePort.findById(student.getId()).isPresent();
        if(alreadyExists){
            throw new ExistingStudentException();
        }
        return this.studentPersistancePort.save(student);
    }

    @Override
    public Student update(Long id, Student student) throws StudentNotFoundException{
        return studentPersistancePort.findById(id)
                .map((savedStudent)-> {
                    savedStudent.setFirstName(student.getFirstName());
                    savedStudent.setLastName(student.getLastName());
                    savedStudent.setAddress(student.getAddress());
                    return studentPersistancePort.save(savedStudent);
                })
                .orElseThrow(()->new StudentNotFoundException("Student with id "+id+" not found"));
    }

    @Override
    public void deleteStudentById(Long id) throws StudentNotFoundException{
        if(studentPersistancePort.findById(id).isEmpty()){
            throw new StudentNotFoundException("Student with id "+id+" not found");
        }
        studentPersistancePort.deleteById(id);
    }
}
