package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.ClinicServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.ClinicPersistancePort;
import com.smile.clinic.smile_clinic.domain.exceptions.ClinicNotFoundException;
import com.smile.clinic.smile_clinic.domain.models.Clinic;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ClinicService implements ClinicServicePort {

    private final ClinicPersistancePort clinicPersistancePort;

    @Override
    public List<Clinic> findAll() {
        return this.clinicPersistancePort.findAll();
    }

    @Override
    public Clinic findById(Long id) {
        return this.clinicPersistancePort.findById(id)
                .orElseThrow(()-> new ClinicNotFoundException("Clinic with id "+id+" not found"));
    }

    @Override
    public List<Clinic> findByOwnerId(int ownerId) {
        return this.clinicPersistancePort.findByOwnerId(ownerId);
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
                    savedClinic.setOwnerId(clinic.getOwnerId());
                    return clinicPersistancePort.save(savedClinic);
                })
                .orElseThrow(()->new ClinicNotFoundException("Clinic with id "+id+" not found"));
    }

    @Override
    public void deleteClinicById(Long id) {
        if(clinicPersistancePort.findById(id).isEmpty()){
            throw new ClinicNotFoundException("Clinic with id "+id+" not found");
        }
        clinicPersistancePort.deleteById(id);
    }
}
