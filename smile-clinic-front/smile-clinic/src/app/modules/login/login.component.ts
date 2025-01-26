import { Component, signal } from '@angular/core';
import { ReactiveFormsModule, FormsModule, FormGroup, FormControl, Validators, AbstractControl} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import {MatFormFieldModule} from '@angular/material/form-field'; 
import { MatInputModule } from '@angular/material/input';
import {MatIconModule} from '@angular/material/icon';
import { LoginService } from '../../../services/login-service.service';
import { Router } from '@angular/router';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';

@Component({
  selector: 'app-login',
  imports: [MatButtonModule, MatCardModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, MatInputModule, MatIconModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {

  constructor(private readonly loginService: LoginService, private readonly router: Router,
    private readonly snackBarService: SnackbarServiceService){ }

  hidePassword: boolean = true;

  usernameFieldErrorMessage = signal('');
  passwordFieldErrorMessage = signal('');

  loginForm = new FormGroup ({
    username: new FormControl('', [Validators.required, Validators.minLength(5)]),
    password: new FormControl('', [Validators.required])
  });


  toggleHidePassword(): void{
    this.hidePassword = !this.hidePassword;
  }

  login(): void {
    const username = this.loginForm.get('username')?.value
    const password = this.loginForm.get('password')?.value

    if(username && password){
      this.loginService.login(username, password).then(() => {
        this.snackBarService.showSuccessSnackBar('Inicio de sesión correcto!');
        this.router.navigate(['layout']);
      }).catch(error => {
        this.snackBarService.showErrorSnackBar(error);
      });
    }
  }
  
  goToSignUpComponent(): void{
    this.router.navigate(['signUp']);
  }

  usernameErrorMessage(): void {
    const usernameForm: any = this.loginForm.get('username');
    if (usernameForm?.hasError('required')) this.usernameFieldErrorMessage.set('Campo obligatorio');
    else if (usernameForm?.hasError('minlength')) this.usernameFieldErrorMessage.set('El nombre de usuario debe tener al menos 5 caracteres');
    else this.usernameFieldErrorMessage.set('');
  }

  passwordErrorMessage(): void {
    const passwordForm: any = this.loginForm.get('password');
    if (passwordForm?.hasError('required')) this.passwordFieldErrorMessage.set('Campo obligatorio');
    else this.passwordFieldErrorMessage.set('');
  }
  
}
