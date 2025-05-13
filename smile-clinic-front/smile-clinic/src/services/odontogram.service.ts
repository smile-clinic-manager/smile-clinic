import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { LocalStorageService } from './local-storage.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';

@Injectable({
  providedIn: 'root'
})
export class OdontogramService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService,
      private localStorageService: LocalStorageService) { }

  
}
