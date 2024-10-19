package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.Student;

import java.util.List;
import java.util.Optional;

public interface StudentPersistancePort {
    Optional<Student> findById(Long id);
    Optional<Student> findByFirstName(String firstName);
    List<Student> findAll();
    Student save(Student student);
    void deleteById(Long id);
}
