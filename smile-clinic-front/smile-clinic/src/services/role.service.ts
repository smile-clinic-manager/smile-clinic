import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { RoleDTO } from '../app/models/RoleDTO';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class RoleService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService) {
  
  }

  getAllRoles(): Promise<RoleDTO[]>{
      return firstValueFrom(
        this.api.get(this.apiEndpointHelper.createUrl('/roles/findAllRoles'))
      )
  }
}
