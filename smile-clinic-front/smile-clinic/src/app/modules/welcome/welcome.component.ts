import { Component } from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTooltipModule} from '@angular/material/tooltip';
import { Router } from '@angular/router';
import { FooterComponent } from '../layout/footer/footer.component';

@Component({
  selector: 'app-welcome',
  imports: [
    MatCardModule, MatButtonModule, MatToolbarModule, MatButtonModule, MatTooltipModule, FooterComponent
  ],
  templateUrl: './welcome.component.html',
  styleUrl: './welcome.component.scss'
})
export class WelcomeComponent {
  constructor(private router: Router){  }


  greet(){
    alert("Welcome to Smile-Clinic!");
  }

  redirectToLoginPage(){
    this.router.navigate(['login']);
  }

  redirectToSignUpPage(){
    this.router.navigate(['signUp']);
  }


}
