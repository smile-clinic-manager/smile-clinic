import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { SignupRequestDTO } from '../app/models/SignupRequestDTO';
import { SignupResponseDTO } from '../app/models/SignupResponseDTO';

@Injectable({
  providedIn: 'root',
})
export class SignupService {
  constructor(
    private api: ApiHttpService,
    private apiEndpointHelper: ApiEndpointHelperService
  ) {}

  signup(
    firstName: string,
    surname: string,
    username: string,
    dni: string,
    email: string,
    confirmEmail: string,
    password: string,
    confirmPassword: string
  ): Promise<boolean> {
    return new Promise((resolve, reject) => {
      const signupRequest: SignupRequestDTO = new SignupRequestDTO(
        firstName,
        surname,
        username,
        dni,
        email,
        confirmEmail,
        password,
        confirmPassword
      );

      this.api
        .post(this.apiEndpointHelper.createUrl('auth/signup'), signupRequest)
        .subscribe({
          next: (response: SignupResponseDTO) => {
            resolve(true);
          },
          error: (error) => {
            alert('Error al registrarse');
            console.error('Error signing up:', error);
            reject(false);
          },
        });
    });
  }
}
