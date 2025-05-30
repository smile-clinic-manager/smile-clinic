import { CommonModule } from '@angular/common';
import { Component, Inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatOptionModule } from '@angular/material/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatIconModule } from '@angular/material/icon';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { AppointmentDTO } from '../../models/AppointmentDTO';

@Component({
  selector: 'app-delete-appointment-form',
  imports: [CommonModule, MatIconModule, MatTimepickerModule, MatOptionModule, MatDialogModule, MatButtonModule],
  templateUrl: './delete-appointment-form.component.html',
  styleUrl: './delete-appointment-form.component.scss'
})
export class DeleteAppointmentFormComponent {

  constructor(@Inject(MAT_DIALOG_DATA) public data: {appointment: AppointmentDTO},
   private dialogRef: MatDialogRef<DeleteAppointmentFormComponent>){ }


  deleteAppointment(): void{

  }

  closeDialog(): void{
    
  }
}
