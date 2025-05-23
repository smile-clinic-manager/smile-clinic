import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { LocalStorageService } from './local-storage.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { TeethDTO } from '../app/models/TeethDTO';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class OdontogramService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService,
      private localStorageService: LocalStorageService) { }

  getAllTeeth(medicalHistoryId: string): Promise<TeethDTO[]>{
    const params: Map<string, any> = new Map();
    params.set('medicalHistoryId', medicalHistoryId);
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/tooth/findAllTeeth', params)
    ));
  }

  //Recuperamos los dientes sin sus medicalRecords
  getToothEntities(): Promise<TeethDTO[]>{
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrl('tooth/getToothEntities'))
    );
  }

}
