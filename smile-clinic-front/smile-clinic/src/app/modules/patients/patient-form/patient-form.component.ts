import { LocalStorageService } from './../../../../services/local-storage.service';
import { DiseaseDTO } from './../../../models/DiseaseDTO';
import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, PristineChangeEvent, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { PatientDTO } from '../../../models/PatientDTO';
import { ClinicDTO } from '../../../models/ClinicDTO';
import { ClinicService } from '../../../../services/clinic.service';
import { MedicalHistoryDTO } from '../../../models/MedicalHistoryDTO';
import { MatOptionModule } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatChipsModule, MatChipInputEvent } from '@angular/material/chips';
import { MatIcon } from '@angular/material/icon';
import { PreviousDiseaseDTO } from '../../../models/PreviousDiseaseDTO';
import { CommonModule } from '@angular/common';
import { PatientService } from '../../../../services/patient.service';
import { SnackbarServiceService } from '../../../../services/snackbar-service.service';

@Component({
  selector: 'app-patient-form',
  imports: [MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule,
  ReactiveFormsModule, MatOptionModule, MatSelectModule, MatChipsModule, MatIcon, CommonModule],
  templateUrl: './patient-form.component.html',
  styleUrl: './patient-form.component.scss'
})
export class PatientFormComponent implements OnInit {

  clinic: ClinicDTO | undefined = undefined;
  isCreating = true;
  previousDiseases: PreviousDiseaseDTO[] = []; //[{'id': 1, 'name':'hepatitis'}, {'id': 2, 'name':'VIH+'}, {'id': 3, 'name':'meningitis'}];

  patientForm = new FormGroup({
    firstName: new FormControl('', [Validators.required]),
    lastName1: new FormControl('', [Validators.required]),
    lastName2: new FormControl(''),
    dni: new FormControl('', [Validators.required, Validators.pattern(/^[0-9]{8}[A-Za-z]$/)]),
    email: new FormControl('', [Validators.required, Validators.email]),
    phoneNumber: new FormControl('', []),
    allergies: new FormControl('', []),
    diseases: new FormControl<PreviousDiseaseDTO[]>([], [])
  });

  constructor(private dialogRef: MatDialogRef<PatientFormComponent>,
    private localStorageService: LocalStorageService,
    private clinicService: ClinicService,
    private patientService: PatientService,
    private snackBarService: SnackbarServiceService,
    @Inject(MAT_DIALOG_DATA) public data: { patient: PatientDTO | null; medicalHistory: MedicalHistoryDTO; diseases: DiseaseDTO[]}
  ){  }

  ngOnInit(): void {
    this.setClinic();
    this.getAllPreviousDiseases().then(() => this.initializeEditPatientForm());
  }

  private initializeEditPatientForm() {
    if (this.data.patient) {
      this.patientForm.get('firstName')?.setValue(this.data.patient.firstName);
      this.patientForm.get('lastName1')?.setValue(this.data.patient.lastName1);
      this.patientForm.get('lastName2')?.setValue(this.data.patient.lastName2);
      this.patientForm.get('dni')?.setValue(this.data.patient.dni);
      this.patientForm.get('email')?.setValue(this.data.patient.email);
      this.patientForm.get('phoneNumber')?.setValue(this.data.patient.phoneNumber);
      this.patientForm.get('allergies')?.setValue(this.data.medicalHistory.allergies);

      // Inicializamos los disease que ya tiene el paciente que nos entra por injeccion del data
      const selectedDiseases = this.previousDiseases.filter(d=> this.data.diseases.some(d2=>Number(d2.id) === Number(d.id)));

      this.patientForm.get('diseases')?.setValue(selectedDiseases);
      this.isCreating = false;
    }
  }

  async setClinic(): Promise<void> {
    this.clinic = await this.clinicService.getClinicById(this.localStorageService.getSelectedGlobalClinic()?.clinicId ?? '');
  }

  async getAllPreviousDiseases(): Promise<void> {
    await this.patientService.getAllPreviousDiseases().then(diseases => {
      this.previousDiseases = diseases;
    })
    .catch(e=> this.snackBarService.showErrorSnackBar('Error al recuperar las patologías del sistema'));
  }

  isValid(): boolean {
    return this.patientForm.valid;
  }

  sendPatientForm(): void {
    if(!this.isValid()) return;
    const patient: PatientDTO = {
      id: '',
      firstName: '',
      lastName1: '',
      lastName2: '',
      dni: '',
      email: '',
      phoneNumber: '',
      medicalHistory: this.data.medicalHistory ?? this.defaultMedicalHistory(),
      clinic: this.clinic!,
    };
    patient.firstName = this.patientForm.get('firstName')?.value ?? '';
    patient.lastName1 = this.patientForm.get('lastName1')?.value ?? '';
    patient.lastName2 = this.patientForm.get('lastName2')?.value ?? '';
    patient.dni = this.patientForm.get('dni')?.value ?? '';
    patient.email = this.patientForm.get('email')?.value ?? '';
    patient.phoneNumber = this.patientForm.get('phoneNumber')?.value ?? '';
    patient.medicalHistory.allergies = this.patientForm.get('allergies')?.value ?? '';
    patient.medicalHistory.previousDiseases = this.patientForm.get('diseases')?.value ?? [];
    patient.clinic = this.clinic!;

    this.dialogRef.close(patient);
  }

  getSelectedDiseases(): PreviousDiseaseDTO[]{
    return this.patientForm.get('diseases')?.value!;
  }

  removeDisease(diseaseToRemove: PreviousDiseaseDTO): void{
    const diseases = this.patientForm.get('diseases')?.value as PreviousDiseaseDTO[] || [];
    const filteredDiseaseList: PreviousDiseaseDTO[] = diseases.filter(disease => disease !== diseaseToRemove);
    this.patientForm.patchValue({...this.patientForm.value, diseases: filteredDiseaseList})
  }

  defaultMedicalHistory(): MedicalHistoryDTO {
    return { id: '', allergies: '', previousDiseases: [] };
  }

}
