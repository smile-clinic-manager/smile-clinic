import { Component } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { UserService } from '../../../services/user.service';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { SysInfoContainerComponent } from "../../shared/sys-info-container/sys-info-container.component";

@Component({
  selector: 'app-add-user-clinic',
  imports: [MatButtonModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, MatInputModule, MatIconModule,
    MatCardModule, CommonModule, SysInfoContainerComponent],
  templateUrl: './add-user-clinic.component.html',
  styleUrl: './add-user-clinic.component.scss'
})
export class AddUserClinicComponent {

  constructor(private userService: UserService, private snackBarService: SnackbarServiceService) { 

  }

  MENSAJE_INFO: string = "Para encontrar el usuario que quieras añadir, pídele su id de usuario, introdúcelo en el campo de búsqueda y pulsa en el botón 'Buscar'";
  foundUser: any = undefined; // TODO: Asignar tipos de dato a foundUser

  userSearchForm = new FormGroup({
    idUser: new FormControl(''),
  })

  findUser() {
    const userId: string = this.userSearchForm.get('idUser')!.value!;
    this.userService.getUserById(userId).then((user) => {
      this.foundUser = user;
      console.log(this.foundUser);
    })
    .catch(() => this.snackBarService.showErrorSnackBar('Usuario no encontrado'))
  }

  addUser(){
    // TODO: llamada al servicio que añade relación entre

  }

}
