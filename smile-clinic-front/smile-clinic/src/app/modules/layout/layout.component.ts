import { Component } from '@angular/core';

import { MatButton } from '@angular/material/button';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatCard } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {provideNativeDateAdapter} from '@angular/material/core';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService} from '../../../services/login-service.service';

@Component({
  selector: 'app-layout',
  imports: [MatButton, MatSlideToggleModule, MatCard, MatDatepickerModule, MatInputModule, MatFormFieldModule, FormsModule,
    ReactiveFormsModule],
  providers: [provideNativeDateAdapter()],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss',
})
export class LayoutComponent {
  isLoggedIn = false;
  token: string = '';

  dateFormControl = new FormControl(null, Validators.required);

  constructor(private loginService: LoginService){ }

  login() {
    this.loginService.login('miguel_rod11', 'Mypass2022#');
  } 

  toggleLogin(){
    this.isLoggedIn = !this.isLoggedIn;
    this.login();
  }

/*   checkInterceptor(){
    const params: Map<string, any> = new Map();
    params.set('token', localStorage.getItem('token')?? '');
    this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/auth/validate-token', params)).subscribe({
      next: (response)=>{
        alert("Validated token: " + response);
      },
      error: (error)=>{
        alert("Error validating the token" + error.message);
      }
    })
  } */

  

}
