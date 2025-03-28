import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { PatientDTO } from '../app/models/PatientDTO';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { ApiHttpService } from './api-http.service';

@Injectable({
  providedIn: 'root'
})
export class PatientService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService) { }

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
}
