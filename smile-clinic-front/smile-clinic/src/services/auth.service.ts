import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { AuthenticationRequestDTO } from '../app/models/AuthenticationRequestDTO';
import { LocalStorageService } from './local-storage.service';
import { AuthenticationResponseDTO } from '../app/models/AuthenticationResponseDTO';
import { SignupRequestDTO } from '../app/models/SignupRequestDTO';
import { RegisteredUserDTO } from '../app/models/RegisteredUserDTO';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private api: ApiHttpService, 
      private apiEndpointHelper: ApiEndpointHelperService,
      private localStorageService: LocalStorageService) { }

  login(username: string, password: string): Promise<boolean> {
    return new Promise((resolve, reject) => {
      const authRequest: AuthenticationRequestDTO = new AuthenticationRequestDTO(username, password);

      this.api.post(this.apiEndpointHelper.createUrl('auth/login'), authRequest).subscribe({
          next: (authResponse: AuthenticationResponseDTO) => {
              this.localStorageService.setTokenInLocalStorage(authResponse.token, authResponse.refreshToken);
              resolve(true);
          },
          error: (error) => {
            reject(error.error.errorMessage);
          },
      });
    });
  }

  logout(): void {
    this.localStorageService.deleteTokens();
  }

  signup(signupRequestDTO: SignupRequestDTO): Promise<boolean> {
        return new Promise((resolve, reject) => {
          this.api
            .post(this.apiEndpointHelper.createUrl('users/signup'), signupRequestDTO)
            .subscribe({
              next: (response: RegisteredUserDTO) => {
                this.localStorageService.setTokenInLocalStorage(response.jwtToken, response.refreshToken);
                resolve(true);
              },
              error: (error) => {
                console.error('Error signing up:', error.error.errorMessage);
                reject(error.error.errorMessage);
              },
            });
      });
    }

}
