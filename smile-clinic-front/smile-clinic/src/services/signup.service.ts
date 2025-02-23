import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { SignupRequestDTO } from '../app/models/SignupRequestDTO';
import { SignupResponseDTO } from '../app/models/RegisteredUserDTO';
import { LocalStorageService } from './local-storage.service';

@Injectable({
  providedIn: 'root',
})
export class SignUpService {
  constructor(
    private api: ApiHttpService,
    private apiEndpointHelper: ApiEndpointHelperService,
    private localStorageService: LocalStorageService
  ) {}

  /* signup(signupRequestDTO: SignupRequestDTO): Promise<boolean> {
      return new Promise((resolve, reject) => {
        this.api
          .post(this.apiEndpointHelper.createUrl('users/signup'), signupRequestDTO)
          .subscribe({
            next: (response: SignupResponseDTO) => {
              this.localStorageService.setTokenInLocalStorage(response.jwtToken);
              resolve(true);
            },
            error: (error) => {
              console.error('Error signing up:', error.error.errorMessage);
              reject(error.error.errorMessage);
            },
          });
    });
  } */
}
