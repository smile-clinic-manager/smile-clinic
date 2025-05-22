import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MedicalHistoryDTO } from '../../models/MedicalHistoryDTO';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { MedicalRecordEntriesService } from '../../../services/medical-record-entries.service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MedicalRecordEntryDTO } from '../../models/MedicalRecordEntryDTO';
import { UserService } from '../../../services/user.service';
import { TreatmentService } from '../../../services/treatment.service';
import { userData } from '../../models/userData';
import { TreatmentDTO } from '../../models/TreatmentDTO';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatTimepickerModule} from '@angular/material/timepicker';

@Component({
  selector: 'app-medical-record-entry-form',
  imports: [MatDialogModule, MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule,
    ReactiveFormsModule, MatOptionModule, MatSelectModule, MatIconModule, CommonModule, MatDatepickerModule, MatTimepickerModule
  ],
  templateUrl: './medical-record-entry-form.component.html',
  providers:[provideNativeDateAdapter()],
  styleUrl: './medical-record-entry-form.component.scss'
})
export class MedicalRecordEntryFormComponent implements OnInit {
  isCreating: boolean = true;
  users: userData[] = [];
  treatments: TreatmentDTO[] = [];

  medicalRecordEntryForm = new FormGroup({
    date: new FormControl('', [Validators.required]),
    time: new FormControl('', [Validators.required]),
    observations: new FormControl(''),
    treatment: new FormControl('', [Validators.required]),
    user: new FormControl('', [Validators.required]),
    
  });

  constructor(private dialogRef: MatDialogRef<MedicalRecordEntryFormComponent>,
      @Inject(MAT_DIALOG_DATA) public data: { clinicId: string, medicalHistoryDTO: MedicalHistoryDTO, medicalRecordEntry: MedicalRecordEntryDTO },
      private snackBarService: SnackbarServiceService, private medicalRecordEntriesService: MedicalRecordEntriesService,
      private userService: UserService, private treatmentService: TreatmentService
    ){ }

  ngOnInit(): void {
    this.getAllDentistsByClinicId();
    this.getAllTreatmentsByClinicId();
    this.initializeMedicalRecordEntryForm()
  }

  initializeMedicalRecordEntryForm() {
    if (this.data.medicalRecordEntry) {
      this.medicalRecordEntryForm.get('date')?.setValue("");
      this.medicalRecordEntryForm.get('time')?.setValue("");
      this.medicalRecordEntryForm.get('observations')?.setValue(this.data.medicalRecordEntry.observations);
      this.medicalRecordEntryForm.get('treatment')?.setValue(this.data.medicalRecordEntry.treatmentInstance.id);
      this.medicalRecordEntryForm.get('user')?.setValue(this.data.medicalRecordEntry.user.id);

      this.isCreating = false;
    }
  }

  getAllDentistsByClinicId():void{
    this.userService.getAllDentistsByClinicId(this.data.clinicId).then((users: userData[])=>{
      this.users = users;
    })
    .catch(() => this.snackBarService.showErrorSnackBar("Error al recuperar los especialistas"));
  }

  getAllTreatmentsByClinicId(): void{
    this.treatmentService.getClinicTreatmentList(this.data.clinicId).then((treatments: TreatmentDTO[])=>{
      this.treatments = treatments;
    })
    .catch(() => this.snackBarService.showErrorSnackBar("Error al recuperar los tratamientos"));
  }

  getCompleteName(user: userData){
    return `${user.firstName} ${user.lastName1} ${user.lastName2}`;
  }

}
