package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.ClinicServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.ClinicPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.ClinicNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.Clinic;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class ClinicService implements ClinicServicePort {

    private final ClinicPersistancePort clinicPersistancePort;

    @Override
    public List<Clinic> findAll() {
        return this.clinicPersistancePort.findAll();
    }

    @Override
    public List<Clinic> findAllByUserId(Long id) {
        List<Clinic> clinics = this.clinicPersistancePort.findAllByUserId(id);
        return clinics;
    }

    @Override
    public Clinic findById(Long id) {
        return this.clinicPersistancePort.findById(id)
                .orElseThrow(()-> new ClinicNotFoundException("Clinic with id "+id+" not found"));
    }

    @Override
    public Clinic findByAddress(String address) {
        return this.clinicPersistancePort.findByAddress(address)
                .orElseThrow(()-> new ClinicNotFoundException("Clinic with address "+address+" not found"));
    }

    @Override
    public Clinic save(Clinic clinic) {
        return this.clinicPersistancePort.save(clinic);
    }

    @Override
    public Clinic update(Long id, Clinic clinic) {
        return clinicPersistancePort.findById(id)
                .map((savedClinic)-> {
                    savedClinic.setAddress(clinic.getAddress());
                    return clinicPersistancePort.save(savedClinic);
                })
                .orElseThrow(()->new ClinicNotFoundException("Clinic with id "+id+" not found"));
    }

    @Override
    public void deleteClinicById(Long id) {
        if(clinicPersistancePort.findById(id).isEmpty()){
            throw new ClinicNotFoundException("Clinic with id "+id+" not found");
        }
        clinicPersistancePort.deleteRolesByClinicId(id);
        clinicPersistancePort.deleteById(id);
    }
}
