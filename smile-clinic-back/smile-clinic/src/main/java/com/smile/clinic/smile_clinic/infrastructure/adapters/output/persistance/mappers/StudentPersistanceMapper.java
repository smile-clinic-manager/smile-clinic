package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.mappers;

import com.smile.clinic.smile_clinic.domain.models.Student;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.StudentEntity;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentPersistanceMapper {

    StudentEntity toStudentEntity(Student student);

    Student toStudent(StudentEntity entity);

    List<Student> toStudentList(List<StudentEntity> entities);

    List<StudentEntity> toStudentEntityList(List<Student> students);

    /*

    Example of how to map clases with different attribute names

    @Mapping(target = "age", source = "edad")
    StudentEntity toStudentEntity2(Student student);

    */
}
