import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatStepperModule } from '@angular/material/stepper';
import { MatIconModule } from '@angular/material/icon';
import { AddUserClinicComponent } from "./add-user-clinic/add-user-clinic.component";
import { MatCardModule } from '@angular/material/card';

@Component({
  selector: 'app-add-user-clinic-stepper',
  imports: [MatButtonModule, MatStepperModule, FormsModule, ReactiveFormsModule, MatFormFieldModule,
    MatInputModule, MatIconModule, AddUserClinicComponent, MatCardModule],
  templateUrl: './add-user-clinic-stepper.component.html',
  styleUrl: './add-user-clinic-stepper.component.scss'
})
export class AddUserClinicStepperComponent {

}
