import { ClinicService } from './../../../../services/clinic.service';
import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import { ClinicDTO } from '../../../models/ClinicDTO';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormControl, FormGroup, FormsModule, Validators } from '@angular/forms';

@Component({
  selector: 'app-clinic-form',
  imports: [MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './clinic-form.component.html',
  styleUrl: './clinic-form.component.scss'
})
export class ClinicFormComponent {

  constructor(private clinicService: ClinicService) {}

  public clinic: ClinicDTO = {
    name: '',
    postalCode: '',
    address: '',
    phoneNumber: '',
    email: '',
    id: '',
    img: '',
    invitations: [],
    treatments: []
  };

  clinicForm = new FormGroup({
    name: new FormControl('', [Validators.required]),
    postalCode: new FormControl('', [Validators.required]),
    address: new FormControl('', [Validators.required]),
    phoneNumber: new FormControl('', [Validators.required]),
    email: new FormControl('', [Validators.required, Validators.email]),
  });

  createClinic(): ClinicDTO {
    const name = this.clinicForm.get('name')?.value ?? '';
    const postalCode = this.clinicForm.get('postalCode')?.value ?? '';
    const address = this.clinicForm.get('address')?.value ?? '';
    const phoneNumber = this.clinicForm.get('phoneNumber')?.value ?? '';
    const email = this.clinicForm.get('email')?.value ?? '';
    this.clinic = {
      name, postalCode, address, phoneNumber, email,
      id: '',
      img: '',
      invitations: [],
      treatments: []
    };
    return this.clinic;
  }

  updateClinic(oldClinic: ClinicDTO): ClinicDTO {
    const name = this.clinicForm.get('name')?.value ?? '';
    const postalCode = this.clinicForm.get('postalCode')?.value ?? '';
    const address = this.clinicForm.get('address')?.value ?? '';
    const phoneNumber = this.clinicForm.get('phoneNumber')?.value ?? '';
    const email = this.clinicForm.get('email')?.value ?? '';
    this.clinic = {
      name, postalCode, address, phoneNumber, email,
      id: oldClinic.id,
      img: oldClinic.img,
      invitations: oldClinic.invitations,
      treatments: oldClinic.treatments
    };
    return this.clinic;
  }
}
