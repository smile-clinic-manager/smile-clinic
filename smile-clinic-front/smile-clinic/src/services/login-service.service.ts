import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { AuthenticationRequestDTO } from '../app/models/AuthenticationRequestDTO';
import { AuthenticationResponseDTO } from '../app/models/AuthenticationRequestDTO copy';
import { LocalStorageService } from './local-storage.service';

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
              alert("Error al iniciar sesión");
              console.error('Error login into application:', error);
              reject(false);
          },
      });
  });

  }

}
