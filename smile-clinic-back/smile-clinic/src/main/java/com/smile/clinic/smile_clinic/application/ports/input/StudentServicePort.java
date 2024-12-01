package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.exceptions.ExistingStudentException;
import com.smile.clinic.smile_clinic.domain.models.Student;

import java.util.List;

/*
* Expone la funcionalidad que se pueden hacer con el estudiante
*/
public interface StudentServicePort {

    Student findById(Long id);

    Student findByFirstName(String firstName);

    List<Student> findAll();

    Student save(Student student) throws ExistingStudentException;

    Student update(Long id, Student student);

    void deleteStudentById(Long id);

}
