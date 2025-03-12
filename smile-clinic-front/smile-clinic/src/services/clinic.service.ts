import { Injectable } from '@angular/core';
import { LocalStorageService } from './local-storage.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { ApiHttpService } from './api-http.service';
import { ClinicDTO } from '../app/models/ClinicDTO';
import { catchError, firstValueFrom, Observable, throwError } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class ClinicService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService) {

  }

  getAllClinics(id: string): Promise<ClinicDTO[]>{
    const params: Map<string, any>  = new Map();
    params.set('id', id)
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/clinics/findAllByUserId', params))
    )
  }

  getClinicById(id: string): Promise<ClinicDTO>{
    const params: Map<string, any>  = new Map();
    params.set('id', id)
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/clinics/findClinicById', params))
    )
  }

}
