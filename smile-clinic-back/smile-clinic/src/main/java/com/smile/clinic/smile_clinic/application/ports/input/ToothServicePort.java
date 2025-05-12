package com.smile.clinic.smile_clinic.application.ports.input;

import com.smile.clinic.smile_clinic.domain.models.Tooth;

import java.util.List;

public interface ToothServicePort {
    List<Tooth> findAllTeeth();
}
