import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PatientService } from '../../../../services/patient.service';
import { PatientDTO } from '../../../models/PatientDTO';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatDialog } from '@angular/material/dialog';
import { PatientFormComponent } from '../patient-form/patient-form.component';

@Component({
  selector: 'app-patients-detail',
  imports: [MatTableModule, MatCardModule, MatMenuModule, MatIconModule, MatDividerModule],
  templateUrl: './patients-detail.component.html',
  styleUrl: './patients-detail.component.scss'
})
export class PatientsDetailComponent implements OnInit {

  patient: PatientDTO | undefined = undefined;
  idParam: string = "";
  readonly dialog = inject(MatDialog);

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
    const dialogRef = this.dialog.open(PatientFormComponent, {
      data: {patient: this.patient}
    });

    dialogRef.afterClosed().subscribe(patient => {
      if(patient === undefined) return;
      this.patientService.updatePatient(Number(this.patient?.id), patient).then(patient => {
        this.patient = patient;
      });
    });
  }

  deletePatient(): void {
    this.patientService.deletePatient(Number(this.patient?.id)).then(patient => {
      this.patient = patient;
    });
  }

}
