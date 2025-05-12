import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { catchError, first, firstValueFrom, Observable, throwError } from 'rxjs';
import { RegisteredUserDTO } from '../app/models/RegisteredUserDTO';
import { RoleDTO } from '../app/models/RoleDTO';

@Injectable({
  providedIn: 'root'
})
export class UserService {
  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService) { }

  getClinicUserList(clinicId: string): Promise<RegisteredUserDTO[]>{
    const params: Map<string, any>  = new Map();
    params.set('clinicId', clinicId)
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/users/usersByClinicId', params)));
  }

  getUserById(userId: string) {
    const params: Map<string, any>  = new Map();
    params.set('userId', userId);
    
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/users/userByUserId', params)));
  }

  assignUserToClinic(userId: string, clinicId: string, roleIds: string[]) {
    return firstValueFrom(
      this.api.post(this.apiEndpointHelper.createUrl('users/assignUserToClinic'), 
        {'userId': userId, 'clinicId': clinicId, 'roleIds': roleIds})
    )
  }
    
  deleteUserFromClinic(clinicId: string, userId: number): Promise<void> {
    const params: Map<string, any>  = new Map();
    params.set('clinicId', clinicId)
    params.set('userId', userId)
    return firstValueFrom(
      this.api.delete(this.apiEndpointHelper.createUrlWithQueryParameters('/users/removeUserFromClinic', params)));
  }

  updateUserClinicRoles(user: RegisteredUserDTO, clinicId: string, selectedRoles: RoleDTO[]) {
    return firstValueFrom(
      this.api.put(this.apiEndpointHelper.createUrl('users/updateRoles'), {'user': user, 'clinicId': clinicId, 'roles': selectedRoles})
    );   
  }

  updateUser(user: RegisteredUserDTO) {
    return firstValueFrom(
      this.api.put(this.apiEndpointHelper.createUrl('users/updateUser'), user)
    );
  }

  deleteUser(userId: string) {
    return firstValueFrom(
      this.api.delete(this.apiEndpointHelper.createUrl('users/deleteUser/' + userId))
    );
  }

}
