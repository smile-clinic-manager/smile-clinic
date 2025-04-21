import { Component, OnInit, inject } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { PatientService } from '../../../../services/patient.service';
import { PatientDTO } from '../../../models/PatientDTO';
import { MatTableModule } from '@angular/material/table';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatIconModule } from '@angular/material/icon';
import { MatDividerModule } from '@angular/material/divider';
import { MatDialog } from '@angular/material/dialog';
import { PatientFormComponent } from '../patient-form/patient-form.component';
import { MatButtonModule } from '@angular/material/button';
import { MatTabsModule } from '@angular/material/tabs';
import { MedicalHistoryService } from '../../../../services/medical-history.service';
import { MedicalHistoryDTO } from '../../../models/MedicalHistoryDTO';
import { PreviousDiseasesService } from '../../../../services/previous-diseases.service';
import { DiseaseDTO } from '../../../models/DiseaseDTO';
import { SnackbarServiceService } from '../../../../services/snackbar-service.service';
import { MatChipsModule } from '@angular/material/chips';

@Component({
  selector: 'app-patients-detail',
  imports: [MatTableModule, MatCardModule, MatMenuModule, MatIconModule, MatDividerModule, MatButtonModule,
    MatTabsModule, MatChipsModule
  ],
  templateUrl: './patients-detail.component.html',
  styleUrl: './patients-detail.component.scss'
})
export class PatientsDetailComponent implements OnInit {

  patient: PatientDTO | undefined = undefined;
  idParam: string = "";
  medicalHistory: MedicalHistoryDTO | undefined = undefined;
  diseases: DiseaseDTO[] = [];
  readonly dialog = inject(MatDialog);

  constructor(private route: ActivatedRoute, private patientService: PatientService, 
    private medicalHistoryService: MedicalHistoryService, private previousDiseasesService: PreviousDiseasesService,
    private snackBarService: SnackbarServiceService, private router: Router) {}

  ngOnInit(): void {
    this.extractId();
    this.findPatient();
    this.findPatientMedicalHistory();
  }
  
  extractId(): string {
    return String(this.route.params.subscribe(params => {
      this.idParam = params['id'];
    }));
  }

  findPatient(): void {
    this.patientService.getPatientById(this.idParam)
    .then(patient => {
      this.patient = patient;
    })
  }

  findPatientMedicalHistory(): void {
    this.medicalHistoryService.getMedicalHistoryByPatientId(this.idParam)
    .then(medicalHistory=>{
      this.medicalHistory = medicalHistory;
    })
    .finally(()=>{
      this.findPreviousDiseases();
    });
  }

  findPreviousDiseases(): void {
    this.previousDiseasesService.getPreviousDiseaseByMedicalHistoryId(this.medicalHistory!.id)
    .then(diseases => {
      this.diseases = diseases;
    })
    .catch(error=> this.snackBarService.showErrorSnackBar("Error al obtener las patologías del paciente"))
  }

  updatePatient(): void {
    const dialogRef = this.dialog.open(PatientFormComponent, {
      data: {
        patient: this.patient,
        medicalHistory: this.medicalHistory,
        diseases: this.diseases
      }
    });

    dialogRef.afterClosed().subscribe(patient => {
      if(patient === undefined) return;

      this.patientService.updatePatient(Number(this.patient?.id), patient)
        .then(patient => {
          this.patient = patient;
        })
        .finally(()=>this.ngOnInit());
    });
  }

  deletePatient(): void {
    this.patientService.deletePatient(Number(this.patient?.id)).then(patient => {
      this.patient = patient;
    })
    .catch(error=> this.snackBarService.showErrorSnackBar("Error al eliminar el paciente"))
    .finally(() => this.router.navigate(['patients-list']));
  }

}
