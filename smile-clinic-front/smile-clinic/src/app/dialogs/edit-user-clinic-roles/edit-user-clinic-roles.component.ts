import { Component, Inject, OnInit } from '@angular/core';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatTableModule } from '@angular/material/table';
import { AddUserClinicStepperComponent } from '../add-user-clinic-stepper/add-user-clinic-stepper.component';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { UserService } from '../../../services/user.service';
import { RegisteredUserDTO } from '../../models/RegisteredUserDTO';
import { RoleDTO } from '../../models/RoleDTO';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { nonEmptyArrayValidator } from '../../shared/validators/non-empty-list-validator';
import { RoleService } from '../../../services/role.service';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-edit-user-clinic-roles',
  imports: [MatTableModule, MatCheckboxModule, MatChipsModule, MatButtonModule],
  templateUrl: './edit-user-clinic-roles.component.html',
  styleUrl: './edit-user-clinic-roles.component.scss'
})
export class EditUserClinicRolesComponent implements OnInit{

  clinicUser: RegisteredUserDTO | null = null;
  clinicId: string = '';
  displayedColumns = ['ROL', 'ACCIONES'];
  dataSource: RoleDTO[] = [];
  reload: boolean = false;

  formSelectRoles = new FormGroup({
    roles: new FormControl<RoleDTO[]>([], [Validators.required, nonEmptyArrayValidator()])
  })

  constructor(@Inject(MAT_DIALOG_DATA) public data: {clinicId: string, clinicUser: RegisteredUserDTO}, 
    private dialogRef: MatDialogRef<AddUserClinicStepperComponent>, private userService: UserService,
    private snackBarService:  SnackbarServiceService, private roleService: RoleService){  }

  ngOnInit(): void {
    this.clinicUser = this.data.clinicUser;
    this.clinicId = this.data.clinicId;
    this.setDataSource();
  }

  setDataSource(): void{
    this.roleService.getAllRoles()
      .then((roles)=>{
        this.dataSource = roles;
      })
      .catch(e=> this.snackBarService.showErrorSnackBar('Error al rescatar los roles'))
      .finally(()=>{
        this.togleAssignedRoles();
      })
  }

  togleAssignedRoles(): void {
    this.formSelectRoles.patchValue({roles: this.clinicUser!.roles!});

    console.log(this.clinicUser!.roles!);
    console.log('forms value');
    console.log(this.formSelectRoles.get('roles')!.value);
  }
  

  toggleSelectRole(isChecked: boolean, element: RoleDTO): void {
    let updatedRoles = [];
    if(isChecked){
      updatedRoles = [...(this.formSelectRoles.get('roles')?.value || []), element];
    }else{
      updatedRoles = (this.formSelectRoles.get('roles')?.value || []).filter(role => role.id !== element.id);
    }
    this.formSelectRoles.patchValue({roles: updatedRoles});
  } 

  roleIsSelected(element: RoleDTO): boolean{
    return this.formSelectRoles.value.roles?.some(role => role.id === element.id) ?? false;
  }

  isOnlyOneRoleSelected(element: RoleDTO): boolean{
    const oneRoleSelected: boolean = (this.formSelectRoles.value.roles?.length ?? 0) === 1;
    return oneRoleSelected && this.roleIsSelected(element);
  }

  enviarFormulario(): void{
    const selectedRoles: RoleDTO[] = this.formSelectRoles!.get('roles')!.value!;
    this.userService.updateUserClinicRoles(this.clinicUser!, this.clinicId, selectedRoles);
    this.reload = true;
    this.closeDialog();
  }

  closeDialog(): void {
    this.dialogRef.close(this.reload);
  }

}
