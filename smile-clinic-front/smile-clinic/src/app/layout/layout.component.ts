import { Component, OnInit } from '@angular/core';
import { ApiHttpService } from '../../services/api-http.service';
import { ApiEndpointHelperService } from '../../services/api-endpoint-helper.service';
import { HttpClient } from '@angular/common/http';
import { AuthenticationRequestDTO } from '../models/AuthenticationRequestDTO';
import { AuthenticationResponseDTO } from '../models/AuthenticationRequestDTO copy';
import { window } from 'rxjs';

@Component({
  selector: 'app-layout',
  imports: [],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss'
})
export class LayoutComponent {
  isLoggedIn = false;
  token: string = '';

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService){ }

  login() {
    //mover a servicio de auth o algo. TEMPORALMENTE AQUÍ
    const authRequest: AuthenticationRequestDTO = new AuthenticationRequestDTO('miguel_rod11', 'Mypass2022#');
    console.log('Login method triggered'); // Debug log
    this.api.post(this.apiEndpointHelper.createUrl('auth/login'), authRequest).subscribe({
      next: (authResponse: AuthenticationResponseDTO) => {
        this.token = authResponse.token;
        alert('Token: '+ this.token);
        localStorage.setItem('token', this.token);
      },
      error: (error) => console.error('Error:', error),
    });
  }

  toggleLogin(){
    this.isLoggedIn = !this.isLoggedIn;
    this.login();
  }

  

}
