import { Component } from '@angular/core';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { UserService } from '../../../services/user.service';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';

@Component({
  selector: 'app-add-user-clinic',
  imports: [MatButtonModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, MatInputModule, MatIconModule],
  templateUrl: './add-user-clinic.component.html',
  styleUrl: './add-user-clinic.component.scss'
})
export class AddUserClinicComponent {

  constructor(private userService: UserService, private snackBarService: SnackbarServiceService) {  }
  foundUser: any = undefined; // TODO: Asignar tipos de dato a foundUser
  searchUserForm = new FormControl('', Validators.required)

  findUser() {
    this.userService.getUserById('2').then((user) => {
      this.foundUser = user;
    })
    .catch(() => this.snackBarService.showErrorSnackBar('Usuario no encontrado'))
  }

}
