import { Component, Input } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { UserService } from '../../../../services/user.service';
import { SnackbarServiceService } from '../../../../services/snackbar-service.service';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { SysInfoContainerComponent } from "../../../shared/sys-info-container/sys-info-container.component";
import {MatCheckboxModule} from '@angular/material/checkbox';
import { RegisteredUserDTO } from '../../../models/RegisteredUserDTO';
import { MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-add-user-clinic',
  imports: [MatButtonModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, MatInputModule, MatIconModule,
    MatCardModule, CommonModule, SysInfoContainerComponent, MatCheckboxModule, MatTooltipModule],
  templateUrl: './add-user-clinic.component.html',
  styleUrl: './add-user-clinic.component.scss'
})
export class AddUserClinicComponent {
  MENSAJE_INFO: string = "Para encontrar el usuario que desee añadir, pídale su id de usuario, introdúzcalo en el campo de búsqueda y pulse en el botón 'Buscar'.";
  
  foundUser: any = undefined; // TODO: Asignar tipos de dato a foundUser
  userSearchForm = new FormGroup({
    idUser: new FormControl(''),
  })

  @Input() userForm!: FormGroup;
  @Input() clinicUsers!: RegisteredUserDTO[];

  constructor(private userService: UserService, private snackBarService: SnackbarServiceService) { 

  }

  findUser() {
    const userId: string = this.userSearchForm.get('idUser')!.value!;

    this.userForm.get('user')!.setValue(null);

    this.userService.getUserById(userId).then((user) => {
      this.foundUser = user;
    })
    .catch(() => this.snackBarService.showErrorSnackBar('Usuario no encontrado'))
  }

  selectUser(checked: boolean): void{
    const user = checked ? this.foundUser : null;
    this.userForm.get('user')!.setValue(user); 
  }

  isUserAlreadyInClinic(): boolean{
    return this.clinicUsers.some((user: RegisteredUserDTO)=> user.id === this.foundUser.id);
  }

}
