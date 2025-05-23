package com.smile.clinic.smile_clinic.application.ports.output;

import com.smile.clinic.smile_clinic.domain.models.Tooth;

import java.util.List;

public interface ToothServicePersistancePort {
    List<Tooth> findAllTeeth();

    List<Tooth> getToothEntities();
}
