import { Component } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormGroup, FormControl} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field'; 
import { MatInputModule } from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import { LoginService } from '../../../services/login-service.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  imports: [MatButtonModule, MatCardModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, MatInputModule, MatIconModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  constructor(private loginService: LoginService, private router: Router){ }

  hidePassword: boolean = true;

  loginForm = new FormGroup ({
    username: new FormControl(''),
    password: new FormControl('')
  });


  toggleHidePassword(){
    this.hidePassword = !this.hidePassword;
  }

  checkValues(){
    const username = this.loginForm.get('username')?.value;
    const password = this.loginForm.get('password')?.value;

    console.log(username); // Logs the username value
    console.log(password); // Logs the password value

  }

  login() {
    const username = this.loginForm.get('username')?.value
    const password = this.loginForm.get('password')?.value

    if(username && password){

      this.loginService.login(username, password).then(isSuccess => {
        if (isSuccess) {
          console.log('Login successful');
          this.router.navigate(['layout']);
        } else {
          console.log('Login failed');
        }
      }).catch(error => {
        console.error('Error during login: ', error);
      });

    }
  }
  
  goToSignUpComponent(){
    this.router.navigate(['signUp'])
  }
  
}
