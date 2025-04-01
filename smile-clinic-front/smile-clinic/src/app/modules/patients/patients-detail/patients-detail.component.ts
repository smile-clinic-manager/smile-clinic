import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../../../../services/patient.service';
import { PatientDTO } from '../../../models/PatientDTO';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-patients-detail',
  imports: [MatTableModule, MatCardModule, MatMenuModule, MatIconModule, MatDividerModule],
  templateUrl: './patients-detail.component.html',
  styleUrl: './patients-detail.component.scss'
})
export class PatientsDetailComponent implements OnInit {

  patient: PatientDTO | undefined = undefined;
  idParam: string = "";

  constructor(private route: ActivatedRoute,
    private patientService: PatientService) {}

    ngOnInit(): void {
      this.extractId();
      this.findPatient();
    }
  
  findPatient(): void {
    this.patientService.getPatientById(this.idParam).then(patient => {
      this.patient = patient;
    });
  }

  extractId(): string {
    return String(this.route.params.subscribe(params => {
      this.idParam = params['id'];
    }));
  }

  updatePatient(): void {
    //TODO implement this
  }

  deletePatient(): void {
    //TODO implement this
  }

}
