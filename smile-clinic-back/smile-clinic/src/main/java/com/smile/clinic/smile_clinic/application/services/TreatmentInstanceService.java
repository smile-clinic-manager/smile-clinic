package com.smile.clinic.smile_clinic.application.services;

import com.smile.clinic.smile_clinic.application.ports.input.TreatmentInstanceServicePort;
import com.smile.clinic.smile_clinic.application.ports.output.TreatmentInstancePersistancePort;
import com.smile.clinic.smile_clinic.domain.models.Treatment;
import com.smile.clinic.smile_clinic.domain.models.TreatmentInstance;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TreatmentInstanceService implements TreatmentInstanceServicePort {

    private final TreatmentInstancePersistancePort treatmentInstancePersistancePort;

    @Override
    public TreatmentInstance findById(Long id) {
        return treatmentInstancePersistancePort.findById(id);
    }

    @Override
    public List<TreatmentInstance> findAll() {
        return treatmentInstancePersistancePort.findAll();
    }

    @Override
    public List<TreatmentInstance> findByPatientId(Long id) {
        return treatmentInstancePersistancePort.findByPatientId(id);
    }

    @Override
    public TreatmentInstance save(TreatmentInstance treatmentInstance) {
        return treatmentInstancePersistancePort.save(treatmentInstance);
    }

    @Override
    public TreatmentInstance update(Long id, TreatmentInstance treatmentInstance) {
        return treatmentInstancePersistancePort.update(id, treatmentInstance);
    }

    @Override
    public void delete(TreatmentInstance treatmentInstance) {
        treatmentInstancePersistancePort.deleteMedicalRecordsByTreatmentInstanceId(treatmentInstance.getId());
        treatmentInstancePersistancePort.delete(treatmentInstance);
    }

    @Override
    public void deleteByPatientId(Long id) {
        List<TreatmentInstance> PatientTreatmentInstances =  treatmentInstancePersistancePort.findByPatientId(id);
        for(TreatmentInstance t : PatientTreatmentInstances){
            this.delete(t);
        }
    }

    // Recupera el tratamiento escogido en el front y copia sus propiedades a una instancia del tratamiento
    // específico para esa visita.
    public TreatmentInstance createInstanceFromTreatmentDTO(Long treatmentId) {
        Treatment treatmentToCopy = treatmentInstancePersistancePort.findTreatmentToCopy(treatmentId);
        TreatmentInstance treatmentInstance = new TreatmentInstance();
        treatmentInstance.setName(treatmentToCopy.getName());
        treatmentInstance.setNotes(treatmentToCopy.getNotes());
        treatmentInstance.setPrice(treatmentToCopy.getPrice());
        return treatmentInstancePersistancePort.save(treatmentInstance);

    }
}
