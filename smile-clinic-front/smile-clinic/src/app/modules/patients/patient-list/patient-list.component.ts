import { Component, OnInit, inject } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableModule } from '@angular/material/table';
import { PatientDTO } from '../../../models/PatientDTO';
import { PatientService } from '../../../../services/patient.service';
import { Router } from '@angular/router';
import { PatientFormComponent } from '../patient-form/patient-form.component';
import { SnackbarServiceService } from '../../../../services/snackbar-service.service';
import { MatDialog } from '@angular/material/dialog';

@Component({
  selector: 'app-patient-list',
  imports: [MatTableModule, MatIconModule, MatButtonModule],
  templateUrl: './patient-list.component.html',
  styleUrl: './patient-list.component.scss'
})
export class PatientListComponent implements OnInit {

  displayedColumns: string[] = ["NOMBRE", "APELLIDOS", "ACCIONES"];
  dataSource: PatientDTO[] = [];
  readonly dialog = inject(MatDialog);

  constructor(private router: Router,
    private patientService: PatientService,
    private snackBarService: SnackbarServiceService) {}

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

  viewPatient(id: string): void {
    if(id !== null || id === undefined) this.router.navigate(['patient-detail', id]);
  }

  createPatient(): void {
    const dialogRef = this.dialog.open(PatientFormComponent, {
      data: {patient: null}
    });

    dialogRef.afterClosed().subscribe((patient: any)  => {
      if(patient === undefined) return;
      this.patientService.createPatient(patient).then(() => {
        this.snackBarService.showSuccessSnackBar('Patient created');
        this.findAll();
      })
      .catch((error) => this.snackBarService.showErrorSnackBar(error))
    });
  }

}
