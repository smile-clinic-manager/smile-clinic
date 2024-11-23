package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.StudentEntity;
import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentEntityRepository extends JpaRepository<StudentEntity, Long> {

}
