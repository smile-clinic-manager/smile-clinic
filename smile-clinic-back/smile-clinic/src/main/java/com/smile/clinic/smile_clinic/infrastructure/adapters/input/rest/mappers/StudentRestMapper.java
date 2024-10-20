package com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.mappers;

import com.smile.clinic.smile_clinic.domain.models.Student;
import com.smile.clinic.smile_clinic.infrastructure.adapters.input.rest.models.StudentDTO;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;

import java.util.List;

//Si no hay atributos mapeables se ignoran
@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface StudentRestMapper {
    Student toStudent(StudentDTO studentDTO); //Si ambas clases son iguales, no hace falta la anotacion
                                                                // @Mapping ya que los campo se llaman igual.
    StudentDTO toStudentDTO(Student student);

    List<Student> toStudentList(List<StudentDTO> studentsDTO);
    List<StudentDTO> toStudentDTOList(List<Student> students);
}
