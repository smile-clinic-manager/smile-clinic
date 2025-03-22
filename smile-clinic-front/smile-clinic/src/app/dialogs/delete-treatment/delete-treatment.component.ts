import { Component, Inject, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { RegisteredUserDTO } from '../../models/RegisteredUserDTO';
import { UserService } from '../../../services/user.service';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { DeleteUserClinicComponent } from '../delete-user-clinic/delete-user-clinic.component';
import { TreatmentDTO } from '../../models/TreatmentDTO';
import { TreatmentService } from '../../../services/treatment.service';

@Component({
  selector: 'app-delete-treatment',
  imports: [MatButtonModule],
  templateUrl: './delete-treatment.component.html',
  styleUrl: './delete-treatment.component.scss'
})
export class DeleteTreatmentComponent implements OnInit{

  constructor(private dialogRef: MatDialogRef<DeleteUserClinicComponent>,
      @Inject(MAT_DIALOG_DATA) public data: { treatment: TreatmentDTO; }, 
      private treatmentService: TreatmentService, private snackBarService: SnackbarServiceService
    ){ }
  
    ngOnInit(): void {
      console.log(this.data);
    }
  
    reload: boolean = true;
  
    deleteTreatment(){
      this.treatmentService.deleteTreatment(this.data.treatment.id)
        .then(() => {
          this.reload = true;
          this.snackBarService.showSuccessSnackBar('Tratamiento eliminado con éxito');
        }).catch(() => {
          this.reload = false;
          this.snackBarService.showErrorSnackBar('Error al eliminar el tratamiento');
        }).finally(() => {
          this.closeDialog(this.reload);
        });
    }
  
    closeDialog(reload: boolean): void{
      this.dialogRef.close(reload);
    }

}
