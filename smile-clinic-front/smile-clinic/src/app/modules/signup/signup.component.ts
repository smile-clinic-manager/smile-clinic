import { Component, signal } from '@angular/core';
import {
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
import { SignUpService } from '../../../services/signup.service';
import { Router } from '@angular/router';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
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

  emailMatchValidator(control: FormControl) {
    const email = control.root.get('email');
    return email && control.value !== email.value ? { emailMismatch: true } : null;
  }

  passwordMatchValidator(): void {
    const password = this.signUpForm.get('password');
    const repeatPassword = this.signUpForm.get('repeatPassword');
    if (repeatPassword!.value !== password!.value) this.signUpForm.get('repeatPassword')!.setErrors({ passwordMismatch: 'Las contraseñas no coinciden' })
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
// Comprobar que las contraseñas coincidan
    if(nameField==='repeatPassword') {
      console.log('repeat');
      console.log(this.signUpForm);
      this.passwordMatchValidator();
    }
    if(form?.hasError('passwordMismatch')) errorMessage = 'Las contraseñas no coinciden';

    this.formErrors.update(errors=> ({ ...errors, [nameField]: errorMessage })); 
    
    console.log(this.signUpForm);
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
      
      this.signUpService
      .signup(signupRequestDTO)
      .then(() => {
        this.snackBarService.showSuccessSnackBar('Registro realizado con éxito');
        this.router.navigate(['/home']);
      })
      .catch(() => {
        this.snackBarService.showErrorSnackBar('Error al registrarse');
      });
    }
    //Por si por alguna razón alguien logra saltarse el check del botón inválido (en teoría nunca debería ejecutarse)
    else this.snackBarService.showErrorSnackBar('El formulario debe ser válido para poder registrarse')
  }

}