import { ClinicService } from './../../../../services/clinic.service';
import { LocalStorageService } from './../../../../services/local-storage.service';
import { ClinicDTO } from './../../../models/ClinicDTO';
import { DiseaseDTO } from './../../../models/DiseaseDTO';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { PatientDTO } from '../../../models/PatientDTO';

@Component({
  selector: 'app-patient-form',
  imports: [MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule,
  ReactiveFormsModule],
  templateUrl: './patient-form.component.html',
  styleUrl: './patient-form.component.scss'
})
export class PatientFormComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<PatientFormComponent>,
    private localStorageService: LocalStorageService,
    private clinicService: ClinicService,
    @Inject(MAT_DIALOG_DATA) public data: { patient: PatientDTO | null; }
  ){}

  clinic: ClinicDTO = {
    id: '',
    name: '',
    postalCode: '',
    address: '',
    phoneNumber: '',
    email: '',
    img: '',
    treatments: []
  }
  isCreating = true;

  async ngOnInit(): Promise<void> {
    this.clinic = await this.clinicService.getClinicById(this.localStorageService.getSelectedGlobalClinic()?.clinicId ?? '');
    if (this.data.patient) {
      this.patientForm.get('firstName')?.setValue(this.data.patient.firstName);
      this.patientForm.get('lastName1')?.setValue(this.data.patient.lastName1);
      this.patientForm.get('lastName2')?.setValue(this.data.patient.lastName2);
      this.patientForm.get('dni')?.setValue(this.data.patient.dni);
      this.patientForm.get('email')?.setValue(this.data.patient.email);
      this.patientForm.get('phoneNumber')?.setValue(this.data.patient.phoneNumber);
      this.patientForm.get('allergies')?.setValue(this.data.patient.allergies);
      this.patientForm.get('diseases')?.setValue(String(this.data.patient.diseases));
      this.isCreating = false;
    }
  }

  patientForm = new FormGroup({
    id: new FormControl('', [Validators.required]),
    firstName: new FormControl('', [Validators.required]),
    lastName1: new FormControl('', [Validators.required]),
    lastName2: new FormControl('', [Validators.required]),
    dni: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
    phoneNumber: new FormControl('', [Validators.required]),
    allergies: new FormControl('', [Validators.required]),
    diseases: new FormControl('', [Validators.required]),
  });

  isValid(): boolean {
    return this.patientForm.valid;
  }

  sendPatientDTO(): void {
    if(!this.isValid()) return;
    const patient: PatientDTO = {
      id: '',
      firstName: '',
      lastName1: '',
      lastName2: '',
      dni: '',
      email: '',
      phoneNumber: '',
      allergies: '',
      diseases: [],
      clinic: this.clinic,
    };
    patient.firstName = this.patientForm.get('firstName')?.value ?? '';
    patient.lastName1 = this.patientForm.get('lastName1')?.value ?? '';
    patient.lastName2 = this.patientForm.get('lastName2')?.value ?? '';
    patient.dni = this.patientForm.get('dni')?.value ?? '';
    patient.email = this.patientForm.get('email')?.value ?? '';
    patient.phoneNumber = this.patientForm.get('phoneNumber')?.value ?? '';
    patient.allergies = this.patientForm.get('allergies')?.value ?? '';
    patient.diseases = this.patientForm.get('diseases')?.value as unknown as DiseaseDTO[] ?? [];
    patient.clinic = this.clinic;
    this.dialogRef.close(patient);
  }

}
