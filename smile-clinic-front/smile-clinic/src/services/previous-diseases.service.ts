import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { LocalStorageService } from './local-storage.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { MedicalHistoryDTO } from '../app/models/MedicalHistoryDTO';
import { firstValueFrom } from 'rxjs';
import { DiseaseDTO } from '../app/models/DiseaseDTO';

@Injectable({
  providedIn: 'root'
})
export class PreviousDiseasesService {

 constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService,
      private localStorageService: LocalStorageService) { }

  getPreviousDiseaseByMedicalHistoryId(medicalHistoryId: string): Promise<DiseaseDTO[]> {
    const params: Map<string, any> = new Map();
    params.set('medicalHistoryId', medicalHistoryId);
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper
        .createUrlWithQueryParameters('/previous-diseases/getByMedicalHistoryId', params))
    )
  }

}
