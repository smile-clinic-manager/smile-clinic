import { Component, inject, Inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatStepperModule } from '@angular/material/stepper';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { STEPPER_GLOBAL_OPTIONS } from '@angular/cdk/stepper';
import { MatTooltipModule } from '@angular/material/tooltip';
import { nonEmptyArrayValidator } from '../../shared/validators/non-empty-list-validator';
import { RoleDTO } from '../../models/RoleDTO';
import { RegisteredUserDTO } from '../../models/RegisteredUserDTO';
import { UserService } from '../../../services/user.service';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { MatChipsModule } from '@angular/material/chips';
import { SysInfoContainerComponent } from '../../shared/sys-info-container/sys-info-container.component';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-update-user-clinic',
  imports: [MatButtonModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, MatInputModule, MatIconModule,
      MatCardModule, CommonModule, MatCheckboxModule, MatTooltipModule],
  templateUrl: './update-user-clinic.component.html',
  styleUrl: './update-user-clinic.component.scss'
})
export class UpdateUserClinicComponent implements OnInit{

  constructor(@Inject(MAT_DIALOG_DATA) public data: {clinicId: string, clinicUser: RegisteredUserDTO}, 
    private dialogRef: MatDialogRef<UpdateUserClinicComponent>, private userService: UserService,
    private snackBarService:  SnackbarServiceService){  }

  private _formBuilder = inject(FormBuilder);
  reload: boolean = true;
  clinicId : string = '';
  clinicUser: RegisteredUserDTO | null = null;

  selectRolesFormGroup = this._formBuilder.group({
    roles: [[], [Validators.required, nonEmptyArrayValidator()]]
  })

  ngOnInit(){
    this.clinicId = this.data.clinicId;
    this.clinicUser = this.data.clinicUser;
  }

  sendFormUpdateUserClinic(): void {
    const selectedRoles: RoleDTO[] = this.selectRolesFormGroup.get('roles')!.value!;
    this.userService.updateUserClinicRoles(this.clinicUser!, this.clinicId, selectedRoles);
  }


}
