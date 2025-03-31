import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTable, MatTableModule } from '@angular/material/table';
import { PatientDTO } from '../../../models/PatientDTO';
import { PatientService } from '../../../../services/patient.service';

@Component({
  selector: 'app-patient-list',
  imports: [MatTableModule, MatIconModule, MatButtonModule],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.scss'
})
export class PatientListComponent implements OnInit {

  displayedColumns: string[] = ["NOMBRE", "APELLIDOS"];
  dataSource: PatientDTO[] = [];

  constructor(private patientService: PatientService) {}

  ngOnInit(): void {
    this.findByActiveClinicId();
  }

  findAll(): void {
    this.patientService.getAllPatients()
    .then(patients => {
      this.dataSource = patients;
    });
  };

  findByActiveClinicId(): void {
    this.patientService.getPatientsByActiveClinicId()
    .then(patients => {
      this.dataSource = patients;
    });
  }

}
