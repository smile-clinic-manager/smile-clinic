import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { AppointmentDTO } from '../../models/AppointmentDTO';
import { AppointmentService } from '../../../services/appointment.service';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';

@Component({
  selector: 'app-delete-appointment-form',
  imports: [CommonModule, MatIconModule, MatTimepickerModule, MatOptionModule, MatDialogModule, MatButtonModule],
  templateUrl: './delete-appointment-form.component.html',
  styleUrl: './delete-appointment-form.component.scss'
})
export class DeleteAppointmentFormComponent {
  reload: boolean = true;

  constructor(@Inject(MAT_DIALOG_DATA) public data: {appointment: AppointmentDTO},
    private dialogRef: MatDialogRef<DeleteAppointmentFormComponent>, private appointmentService: AppointmentService,
    private snackBarService: SnackbarServiceService){ }

  deleteAppointment(): void{
    this.appointmentService.deleteAppointment(this.data.appointment.id)
    .then(() => {
      this.reload = true;
      this.snackBarService.showSuccessSnackBar('Cita eliminada con éxito');
    }).catch(() => {
      this.reload = false;
      this.snackBarService.showErrorSnackBar('Error al eliminar la cita');
    }).finally(() => {
      this.closeDialog(this.reload);
    });
  }

  closeDialog(reload: boolean): void{
    this.dialogRef.close(reload);
  }
}
