import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { PatientDTO } from '../app/models/PatientDTO';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { ApiHttpService } from './api-http.service';
import { LocalStorageService } from './local-storage.service';
import { ClinicDTO } from '../app/models/ClinicDTO';
import { PreviousDiseaseDTO } from '../app/models/PreviousDiseaseDTO';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService,
    private localStorageService: LocalStorageService) { }

  getAllPatients(): Promise<PatientDTO[]> {
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrl('patients/findAllPatients'))
    )
  }

  getPatientById(id: string): Promise<PatientDTO> {
    const params: Map<string, any> = new Map();
    params.set('id', id)
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/patients/findPatientById', params))
    )
  }

  getPatientsByActiveClinicId(): Promise<PatientDTO[]> {
    const activeClinicId: string = this.localStorageService.getSelectedGlobalClinic()?.clinicId || '';
    const params: Map<string, any> = new Map();
    params.set('clinicId', activeClinicId)
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/patients/findPatientsByClinicId', params))
    )
  }

  createPatient(patient: PatientDTO): Promise<PatientDTO> {
    return firstValueFrom(
      this.api.post(this.apiEndpointHelper.createUrl('patients/savePatient'), patient)
    )
  }

  updatePatient(id: Number, patient: PatientDTO): Promise<PatientDTO> {
    return firstValueFrom(
      this.api.put(this.apiEndpointHelper.createUrlWithQueryParameters('/patients/updatePatient', new Map([['id', id]])), patient)
    )
  }

  deletePatient(id: Number): Promise<PatientDTO> {
    return firstValueFrom(
      this.api.delete(this.apiEndpointHelper.createUrlWithQueryParameters('/patients/deletePatient', new Map([['id', id]])))
    )
  }

  getAllPreviousDiseases(): Promise<PreviousDiseaseDTO[]>{
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrl('previous-diseases/getAllDiseases'))
    );
  }

}
