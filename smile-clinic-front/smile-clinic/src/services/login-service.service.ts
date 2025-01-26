import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { AuthenticationRequestDTO } from '../app/models/AuthenticationRequestDTO';
import { ErrorResponseDTO } from '../app/models/ErrorResponseDTO';
import { LocalStorageService } from './local-storage.service';
import { AuthenticationResponseDTO } from '../app/models/AuthenticationResponseDTO';

@Injectable({
  providedIn: 'root'
})
export class LoginService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService,
    private localStorageService: LocalStorageService) { 

  }

  login(username: string, password: string): Promise<boolean> {
    return new Promise((resolve, reject) => {
      const authRequest: AuthenticationRequestDTO = new AuthenticationRequestDTO(username, password);
      
      this.api.post(this.apiEndpointHelper.createUrl('auth/login'), authRequest).subscribe({
          next: (authResponse: AuthenticationResponseDTO) => {   
              this.localStorageService.setTokenInLocalStorage(authResponse.token);
              resolve(true);
          },
          error: (error) => {
            console.error('Error login into application: ', error.error.error);
            reject(error.error.error);
          },
      });
  });

  }

}
