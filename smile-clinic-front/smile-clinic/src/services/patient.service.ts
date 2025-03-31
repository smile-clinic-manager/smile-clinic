import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { PatientDTO } from '../app/models/PatientDTO';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { ApiHttpService } from './api-http.service';
import { LocalStorageService } from './local-storage.service';
import { ClinicDTO } from '../app/models/ClinicDTO';

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
}
