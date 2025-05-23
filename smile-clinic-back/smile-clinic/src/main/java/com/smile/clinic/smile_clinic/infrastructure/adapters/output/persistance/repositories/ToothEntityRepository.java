package com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.repositories;

import com.smile.clinic.smile_clinic.infrastructure.adapters.output.persistance.entities.ToothEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ToothEntityRepository extends JpaRepository<ToothEntity, Long> {
    @Query(value = "SELECT * FROM tooth ", nativeQuery = true)
    List<ToothEntity> findAllToothEntities();
}
