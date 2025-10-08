import { Component, Inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { RegisteredUserDTO } from '../../models/RegisteredUserDTO';
import { UserService } from '../../../services/user.service';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';

@Component({
  selector: 'app-delete-user-clinic',
  imports: [MatButtonModule],
  templateUrl: './delete-user-clinic.component.html',
  styleUrl: './delete-user-clinic.component.scss'
})
export class DeleteUserClinicComponent{

  constructor(private dialogRef: MatDialogRef<DeleteUserClinicComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { clinicId: string; user: RegisteredUserDTO }, 
    private userService: UserService, private snackBarService: SnackbarServiceService
  ){ }

  reload: boolean = true;

  deleteUserClinic(){
    this.userService.deleteUserFromClinic(this.data.clinicId, this.data.user.id)
      .then(() => {
        this.reload = true;
        this.snackBarService.showSuccessSnackBar('Usuario eliminado con éxito');
      }).catch(() => {
        this.reload = false;
        this.snackBarService.showErrorSnackBar('Error al eliminar usuario de la clínica');
      }).finally(() => {
        this.closeDialog(this.reload);
      });
  }

  closeDialog(reload: boolean): void{
    this.dialogRef.close(reload);
  }
}
