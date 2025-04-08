import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { firstValueFrom } from 'rxjs';
import { MedicalHistoryDTO } from '../app/models/MedicalHistoryDTO';

@Injectable({
  providedIn: 'root'
})
export class MedicalHistoryService {
  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService,
      private localStorageService: LocalStorageService) { }

  getMedicalHistoryByPatientId(patientId: string): Promise<MedicalHistoryDTO> {
    const params: Map<string, any> = new Map();
    params.set('patientId', patientId);
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper
        .createUrlWithQueryParameters('/medical-history/getMedicalHistoryByPatientId', params))
    )
  }
}
