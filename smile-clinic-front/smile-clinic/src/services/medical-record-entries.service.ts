import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { ApiHttpService } from './api-http.service';
import { LocalStorageService } from './local-storage.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { MedicalRecordEntryDTO } from '../app/models/MedicalRecordEntryDTO';

@Injectable({
  providedIn: 'root'
})
export class MedicalRecordEntriesService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService,
        private localStorageService: LocalStorageService) { }

  getAllMedicalRecordsByMedicalHistory(medicalHistoryId: string): Promise<MedicalRecordEntryDTO[]>{
      const params: Map<string, any> = new Map();
      params.set('medicalHistoryId', medicalHistoryId);
      return firstValueFrom(
        this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/medical-records/getAllMedicalRecordsByMedicalHistory', params)
      ));
  }

}
