import { Component, inject, Inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatStepperModule } from '@angular/material/stepper';
import { MatIconModule } from '@angular/material/icon';
import { AddUserClinicComponent } from "./add-user-clinic/add-user-clinic.component";
import { MatCardModule } from '@angular/material/card';
import { MAT_DIALOG_DATA } from '@angular/material/dialog';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { MatTooltipModule } from '@angular/material/tooltip';
import { SelectRoleComponent } from "./select-role/select-role.component";
import { nonEmptyArrayValidator } from '../../shared/validators/non-empty-list-validator';

@Component({
  selector: 'app-add-user-clinic-stepper',
  imports: [MatButtonModule, MatStepperModule, FormsModule, ReactiveFormsModule, MatFormFieldModule,
    MatInputModule, MatIconModule, AddUserClinicComponent, MatCardModule, MatTooltipModule, SelectRoleComponent],
  providers: [
    {
      provide: STEPPER_GLOBAL_OPTIONS, /* Permite mostrar errores del stepper */
      useValue: {showError: true},
    },
  ],
  templateUrl: './add-user-clinic-stepper.component.html',
  styleUrl: './add-user-clinic-stepper.component.scss'
})
export class AddUserClinicStepperComponent implements OnInit{
  private _formBuilder = inject(FormBuilder);

  selectUserFormGroup = this._formBuilder.group({
    user: [null, Validators.required]
  })

  selectRolesFormGroup = this._formBuilder.group({
    roles: [[], [Validators.required, nonEmptyArrayValidator()]]
  })

  constructor(@Inject(MAT_DIALOG_DATA) public data: {clinicId: string}){  }
  clinicId : string = '';

  ngOnInit(){
    this.clinicId = this.data.clinicId;
    console.log(this.clinicId);
  }
  
  isStepUserValid(): boolean {
    return this.selectUserFormGroup.valid;
  }

  isStepRoleValid(): boolean {
    return this.selectRolesFormGroup.valid;
  }

  addUserToClinic(): void{
    /*

     TODO: 
     1. LLAMAR ENDPOINT DE CREACION DE RELACION USER_CLINIC_ROLE
     2. CUANDO TERMINE CERRAR DIALOG Y MOSTRAR ERROR O OK SNACKBAR

    */
  }
}
