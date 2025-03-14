import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatDialogModule} from '@angular/material/dialog';
import { ClinicDTO } from '../../../models/ClinicDTO';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-clinic-form',
  imports: [MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule],
  changeDetection: ChangeDetectionStrategy.OnPush,
  templateUrl: './clinic-form.component.html',
  styleUrl: './clinic-form.component.scss'
})
export class ClinicFormComponent {
  clinic: ClinicDTO = {
    id: '',
    name: '',
    postalCode: '',
    address: '',
    phoneNumber: '',
    email: '',
    img: '',
    invitations: [],
    treatments: []
  }
}
