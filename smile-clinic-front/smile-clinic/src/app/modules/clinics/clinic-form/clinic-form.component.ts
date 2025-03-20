import { ClinicService } from './../../../../services/clinic.service';
import { ChangeDetectionStrategy, Component, Inject, OnInit, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MAT_DIALOG_DATA, MatDialogModule, MatDialogRef} from '@angular/material/dialog';
import { ClinicDTO } from '../../../models/ClinicDTO';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-clinic-form',
  imports: [MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule,
  ReactiveFormsModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './clinic-form.component.html',
  styleUrl: './clinic-form.component.scss'
})
export class ClinicFormComponent implements OnInit {

  constructor(private dialogRef: MatDialogRef<ClinicFormComponent>,
    @Inject(MAT_DIALOG_DATA) public data: { clinic: ClinicDTO | null; }
  ){ }

  isCreating = true;

  ngOnInit(): void {
    console.log(this.data);
    if (this.data.clinic) {
      this.clinicForm.get('name')?.setValue(this.data.clinic.name);
      this.clinicForm.get('postalCode')?.setValue(this.data.clinic.postalCode);
      this.clinicForm.get('address')?.setValue(this.data.clinic.address);
      this.clinicForm.get('phoneNumber')?.setValue(this.data.clinic.phoneNumber);
      this.clinicForm.get('email')?.setValue(this.data.clinic.email);
      this.isCreating = false;
    }
    console.log(this.clinicForm);
  }

  clinicForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    postalCode: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
  });

  isValid(): boolean {
    return this.clinicForm.valid;
  }

  sendClinicDTO(): void {
    if(!this.isValid()) return;
    const clinic: ClinicDTO = {
      id: '',
      name: '',
      postalCode: '',
      address: '',
      phoneNumber: '',
      email: '',
      img: '',
      invitations: [],
      treatments: []
    };
    clinic.name = this.clinicForm.get('name')?.value ?? '';
    clinic.postalCode = this.clinicForm.get('postalCode')?.value ?? '';
    clinic.address = this.clinicForm.get('address')?.value ?? '';
    clinic.phoneNumber = this.clinicForm.get('phoneNumber')?.value ?? '';
    clinic.email = this.clinicForm.get('email')?.value ?? '';
    this.dialogRef.close(clinic);
  }
}
