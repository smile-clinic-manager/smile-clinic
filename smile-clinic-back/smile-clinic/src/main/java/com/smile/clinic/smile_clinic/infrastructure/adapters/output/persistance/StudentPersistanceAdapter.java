package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance;

import com.smile.clinic.smile_clinic.application.ports.output.StudentPersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Student;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers.StudentPersistanceMapper;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories.StudentEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

/*
* IMPLEMENTA los puertos de SALIDA
*/
@Component
@RequiredArgsConstructor
public class StudentPersistanceAdapter implements StudentPersistancePort {

    private final StudentEntityRepository studentRepository;
    private final StudentPersistanceMapper mapper;

    @Override
    public Optional<Student> findById(Long id) {
        return this.studentRepository.findById(id)
                .map(mapper::toStudent); // Convertimos de StudentEntity a Student
    }

    @Override
    public Optional<Student> findByFirstName(String firstName) {
        return Optional.empty();
    }

    @Override
    public List<Student> findAll() {
        return mapper.toStudentList(this.studentRepository.findAll());
    }

    @Override
    public Student save(Student student) {
        return mapper.toStudent(this.studentRepository.save(mapper.toStudentEntity(student)));
    }

    @Override
    public void deleteById(Long id) {
        this.studentRepository.deleteById(id);
    }
}
