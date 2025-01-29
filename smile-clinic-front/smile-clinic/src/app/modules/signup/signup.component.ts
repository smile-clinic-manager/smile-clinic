import { Component, signal } from '@angular/core';
import {
  FormControl,
  FormGroup,
  FormsModule,
  ReactiveFormsModule,
  Validators,
  ɵFormControlCtor,
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
  constructor() {
    /*private readonly signUpService: SignUpService;
    private readonly router: Router;
    private readonly snackBarService: SnackbarServiceService;*/
  }
}

hidePassword: boolean = true;
const usernameFieldErrorMessage = signal('');
const passwordFieldErrorMessage = signal('');

signUpForm = new FormGroup({
  firstName: new FormControl('', [Validators.required]),
  surname: new FormControl('', [Validators.required]),
  username: new FormControl('', [Validators.required, Validators.minLength(5)]),
  dni: new FormControl('', [
    Validators.required,
    Validators.pattern(/^[0-9]{8}[A-Za-z]$/),
  ]),
  email: new FormControl('', [Validators.required, Validators.email]),
  confirmEmail: new FormControl('', [
    Validators.required,
    Validators.email,
    emailMatchValidator,
  ]),
  password: new FormControl('', [
    Validators.required,
    Validators.pattern(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/),
  ]),
  confirmPassword: new FormControl('', [
    Validators.required,
    this.passwordMatchValidator,
  ]),
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
  const firstName = this.signUpForm.get('firstName')?.value;
  const surname = this.signUpForm.get('surname')?.value;
  const username = this.signUpForm.get('username')?.value;
  const dni = this.signUpForm.get('dni')?.value;
  const email = this.signUpForm.get('email')?.value;
  const confirmEmail = this.signUpForm.get('confirmEmail')?.value;
  const password = this.signUpForm.get('password')?.value;
  const confirmPassword = this.signUpForm.get('confirmPassword')?.value;

  if (this.signUpForm.valid) {
    this.success();
  }
}

success(): void {
  this.signUpService
    .signup(firstName, surname, username, dni, email, confirmEmail, password, confirmPassword)
    .then(() => {
      this.router.navigate(['/login']);
      this.snackBarService.openSnackBar('User created successfully');
    })
    .catch(() => {
      this.snackBarService.openSnackBar('Error creating user');
    });
}
