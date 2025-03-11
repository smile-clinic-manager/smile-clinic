import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { catchError, firstValueFrom, Observable, throwError } from 'rxjs';
import { RegisteredUserDTO } from '../app/models/RegisteredUserDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService) { }

  getClinicUserList(clinicId: string): Promise<RegisteredUserDTO[]>{
    const params: Map<string, any>  = new Map();
    params.set('clinicId', clinicId)
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/users/usersByClinicId', params))
    )
  }

  getUserById(userId: string) {
    const params: Map<string, any>  = new Map();
    params.set('userId', userId);
    
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/users/userByUserId', params)))
  }

}
