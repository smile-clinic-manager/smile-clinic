import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../../../../services/patient.service';
import { PatientDTO } from '../../../models/PatientDTO';
import { MatTableModule } from '@angular/material/table';

@Component({
  selector: 'app-patients-detail',
  imports: [MatTableModule],
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

}
