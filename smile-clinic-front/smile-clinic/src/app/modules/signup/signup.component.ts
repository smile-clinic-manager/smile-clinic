import { Component, signal } from '@angular/core';
import {
  AbstractControl,
  AbstractControl,
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
import { Router } from '@angular/router';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { SignupRequestDTO } from '../../models/SignupRequestDTO';
import { AuthService } from '../../../services/auth.service';

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
  constructor( private readonly authService: AuthService, 
    private readonly router: Router, 
    private readonly snackBarService: SnackbarServiceService) {
  }

  private readonly PASSWORD_PATTERN_ERROR_MESSAGE = 'La contraseña debe contener un dígito, una mayúscula, una minúscula, un caracter especial y una longitud de 12 caracteres';
  private readonly DNI_PATTERN_ERROR_MESSAGE = 'El dni debe estar compuesto de 8 digitos seguido de una letra 11111111A';
  hidePassword = true;
  formFieldErrorMessage = signal('');
  formErrors = signal<{ [key: string]: string }>({});

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
    confirmPassword: new FormControl('', [Validators.required],),
  });

  passwordMatchValidator(): string {
    let mismatchPasswordErrorMessage = '';
    const password = this.signUpForm.get('password');
    const confirmPassword = this.signUpForm.get('confirmPassword');
    if (confirmPassword!.value !== password!.value) {
      this.signUpForm.get('confirmPassword')!.setErrors({ passwordMismatch: 'Las contraseñas no coinciden' })
      mismatchPasswordErrorMessage = 'Las contraseñas no coinciden';
    }
    return mismatchPasswordErrorMessage;
  }

  emailMatchValidator(): string {
    let mismatchPasswordErrorMessage = '';
    const password = this.signUpForm.get('email');
    const confirmPassword = this.signUpForm.get('confirmEmail');
    if (confirmPassword!.value !== password!.value) {
      this.signUpForm.get('confirmEmail')!.setErrors({ emailMismatch: 'Los emails no coinciden' })
      mismatchPasswordErrorMessage = 'Las emails no coinciden';
    }
    return mismatchPasswordErrorMessage;
  }

  toggleHidePassword(): void {
    this.hidePassword = !this.hidePassword;
  }

  getErrorMessage(nameField: string): void {
    let errorMessage: string = '';

    const form: AbstractControl = this.signUpForm.get(nameField)!;
    if (form?.hasError('required')) errorMessage = 'Campo obligatorio';
    else if (form?.hasError('minlength')) errorMessage = `El nombre de usuario debe tener al menos 5 caracteres`;
    else if (form?.hasError('email')) errorMessage = 'La dirección de correo no es válida';
    else if (form?.hasError('pattern') && nameField.includes('dni')) errorMessage = this.DNI_PATTERN_ERROR_MESSAGE;
    else if (form?.hasError('pattern') && nameField.includes('assword')) errorMessage = this.PASSWORD_PATTERN_ERROR_MESSAGE;
    else this.clearFormError(nameField);
// Comprobar que las contraseñas y emails coincidan
    if (nameField === 'confirmPassword' || nameField ==='confirmEmail') {
      errorMessage = this.checkValueMismatch(nameField, form, errorMessage);
    }

    this.formErrors.update(errors=> ({ ...errors, [nameField]: errorMessage })); 
  }

  private checkValueMismatch(nameField: string, form: AbstractControl<any, any>, errorMessage: string) {
    if (nameField === 'confirmPassword' && !form.errors) {
      errorMessage = this.passwordMatchValidator(); // devuelve str vacío o el msj de error si lo hay
    } else if(nameField === 'confirmEmail' && !form.errors){
      errorMessage = this.emailMatchValidator();
    }
    return errorMessage;
  }

  clearFormError(formName: string) {
    this.formErrors.update(errors => {
      const updatedErrors = { ...errors };
      delete updatedErrors[formName]; // Eliminar error asociada a esa clave
      return updatedErrors;
    });
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
      
      this.authService
        .signup(signupRequestDTO)
        .then(() => {
          this.snackBarService.showSuccessSnackBar('Registro realizado con éxito');
          this.router.navigate(['/home']);
        })
        .catch((errorMessage: string) => {
          this.snackBarService.showErrorSnackBar(errorMessage);
        });
    }
    //Por si por alguna razón alguien logra saltarse el check del botón inválido (en teoría nunca debería ejecutarse)
    else this.snackBarService.showErrorSnackBar('El formulario debe ser válido para poder registrarse')
  }

}