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

  //CRUD

  createClinic(clinic: ClinicDTO): Promise<ClinicDTO>{
    return firstValueFrom(
      this.api.post(this.apiEndpointHelper.createUrl('/clinics/createClinic'), clinic)
    )
  }

  updateClinic(id: Number, clinic: ClinicDTO): Promise<ClinicDTO>{
    return firstValueFrom(
      this.api.put(this.apiEndpointHelper.createUrl('/clinics/updateClinic'), id, clinic)
    )
  }

  deleteClinic(id: Number): Promise<ClinicDTO>{
    this.deleteRolesByClinicId(id);
    return firstValueFrom(
      this.api.delete(this.apiEndpointHelper.createUrlWithQueryParameters('/clinics/deleteClinicById', new Map([['id', id]])))
    )
  }

  deleteRolesByClinicId(id: Number): Promise<ClinicDTO>{
    return firstValueFrom(
      this.api.delete(this.apiEndpointHelper.createUrlWithQueryParameters('/clinics/deleteRolesByClinicId', new Map([['id', id]])))
    )
  }

}
