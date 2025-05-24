import { Injectable } from '@angular/core';
import { firstValueFrom } from 'rxjs';
import { ApiHttpService } from './api-http.service';
import { LocalStorageService } from './local-storage.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { MedicalRecordEntryDTO } from '../app/models/MedicalRecordEntryDTO';
import { MedicalRecordEntryFormDTO } from '../app/models/MedicalRecordEntryFormDTO';

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

  createNewMedicalRecordEntry(form: MedicalRecordEntryFormDTO): Promise<void> {
    return firstValueFrom(
      this.api.post(this.apiEndpointHelper.createUrl('medical-records/createMedicalRecordEntry'), form)
    );
  }

  getAllRelatedTeethToMedicalRecord(medicalRecordId: string | undefined) {
    const params: Map<string, any> = new Map();
      params.set('medicalRecordId', medicalRecordId);
      return firstValueFrom(
        this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/medical-records/getAllRelatedTeeth', params)
      ));
  }
}