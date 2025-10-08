import { Component, OnInit, inject } from "@angular/core";
import { ClinicDTO } from '../../../models/ClinicDTO';
import { FormsModule } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { ActivatedRoute } from "@angular/router";
import { TreatmentDTO } from '../../../models/TreatmentDTO';
import {MatTabsModule} from '@angular/material/tabs';
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';
import { ClinicPersonalComponent } from "../clinic-personal/clinic-personal.component";
import { MatButtonModule } from '@angular/material/button';
import { ClinicService } from '../../../../services/clinic.service';
import { ClinicFormComponent } from '../clinic-form/clinic-form.component';
import { MatDialog } from '@angular/material/dialog';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { TreatmentListComponent } from '../../../treatment-list/treatment-list.component';
import { LocalStorageService } from '../../../../services/local-storage.service';
import { RoleDTO } from '../../../models/RoleDTO';


@Component({
  selector: 'app-clinic-detail',
  templateUrl: './clinic-detail.component.html',
  styleUrl: './clinic-detail.component.scss',
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatTabsModule, TreatmentListComponent, MatIconModule, MatCardModule,
    MatMenuModule, MatDividerModule, ClinicPersonalComponent, MatButtonModule, MatProgressSpinnerModule]
})
export class ClinicDetailComponent implements OnInit {

  clinic: ClinicDTO | undefined = undefined;
  idParam: string = "";
  treatment: TreatmentDTO | undefined = undefined;
  readonly dialog = inject(MatDialog);
  userRole: RoleDTO | undefined = undefined;

  constructor(private route: ActivatedRoute, private clinicService: ClinicService, private localStorageService: LocalStorageService) {}

  ngOnInit(): void {
    this.getSelectedRole();
    this.extractId();
    this.findClinic();
  }

  getSelectedRole(): void{
    this.userRole = this.localStorageService.getSelectedGlobalRole() ?? undefined;
  }

  findClinic(): void {
    this.clinicService.getClinicById(this.idParam).then(clinic => {
      this.clinic = clinic;
    });
  }

  extractId(): string {
    return String(this.route.params.subscribe(params => {
      this.idParam = params['id'];
    }));
  }

  updateClinic(): void {
    const dialogRef = this.dialog.open(ClinicFormComponent, {
      data: {clinic: this.clinic}
    });

    dialogRef.afterClosed().subscribe(clinic => {
      if(clinic === undefined) return;
      this.clinicService.updateClinic(Number(this.clinic?.id), clinic).then(clinic => {
        this.clinic = clinic;
      });
    });
  }

  deleteClinic(): void {
    if(this.clinic === undefined) return;
    this.clinicService.deleteClinic(Number(this.clinic.id)).then(clinic => {
      this.clinic = clinic;
    });
  }
}