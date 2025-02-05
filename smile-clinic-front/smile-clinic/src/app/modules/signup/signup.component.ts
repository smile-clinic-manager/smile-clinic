import { Component, signal } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
} from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { SignUpService } from '../../../services/signup.service';
import { Router } from '@angular/router';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { sign } from 'crypto';
import { SignupRequestDTO } from '../../models/SignupRequestDTO';

@Component({
  selector: 'app-sign-up',
  imports: [
    MatButtonModule,
    MatCardModule,
    MatFormFieldModule,
    FormsModule,
    ReactiveFormsModule,
    MatInputModule,
    MatIconModule,
  ],
  templateUrl: './signup.component.html',
  styleUrl: './signup.component.scss',
})
export class SignUpComponent {
  constructor( private readonly signUpService: SignUpService, 
    private readonly router: Router, 
    private readonly snackBarService: SnackbarServiceService) {
  }

  hidePassword = true;
  usernameFieldErrorMessage = signal('');
  passwordFieldErrorMessage = signal('');


  signUpForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName1: new FormControl('', [Validators.required]),
    lastName2: new FormControl('', [Validators.required]),
    username: new FormControl('', [Validators.required, Validators.minLength(5)]),
    dni: new FormControl('', [
      Validators.required,
      Validators.pattern(/^[0-9]{8}[A-Za-z]$/),
    ]),
    email: new FormControl('', [Validators.required, Validators.email]),
    confirmEmail: new FormControl('', [Validators.required, Validators.email]),
    password: new FormControl('', [Validators.required, Validators.pattern(/^(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*[\W_]).{12,}$/)]),
    confirmPassword: new FormControl('', [Validators.required]),
  });

  emailMatchValidator(control: FormControl) {
    const email = control.root.get('email');
    return email && control.value !== email.value ? { emailMismatch: true } : null;
  }

  passwordMatchValidator(control: FormControl) {
    const password = control.root.get('password');
    return password && control.value !== password.value ? { passwordMismatch: true } : null;
  }

  toggleHidePassword(): void {
    this.hidePassword = !this.hidePassword;
  }

  signUp(): void {
    if(this.signUpForm.valid){
      //Maybe refactor in the future: refactor this structure << this.signUpForm.get('firstName')?.value! ?? '' >> into shared method.
      const firstName: string | null = this.signUpForm.get('firstName')?.value! ?? '';
      const lastName1: string | null = this.signUpForm.get('lastName1')?.value! ?? '';
      const lastName2: string | null = this.signUpForm.get('lastName2')?.value! ?? '';
      const username: string | null = this.signUpForm.get('username')?.value! ?? '';
      const dni: string | null = this.signUpForm.get('dni')?.value! ?? '';
      const email: string | null = this.signUpForm.get('email')?.value! ?? '';
      const confirmEmail: string | null = this.signUpForm.get('confirmEmail')?.value! ?? '';
      const password: string | null = this.signUpForm.get('password')?.value! ?? '';
      const confirmPassword: string | null = this.signUpForm.get('confirmPassword')?.value! ?? '';

      const signupRequestDTO: SignupRequestDTO = new SignupRequestDTO(firstName,lastName1, lastName2, username, dni, email, confirmEmail, password, confirmPassword);
      
      this.signUpService
      .signup(signupRequestDTO)
      .then(() => {
        this.snackBarService.showSuccessSnackBar('Registro realizado con éxito');
        this.router.navigate(['/login']);
      })
      .catch(() => {
        this.snackBarService.showErrorSnackBar('Error al registrarse') //openSnackBar('Error creating user');
      });
    }
  }

}