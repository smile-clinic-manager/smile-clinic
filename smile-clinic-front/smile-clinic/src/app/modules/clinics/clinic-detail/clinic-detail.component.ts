import { ApiEndpointHelperService } from '../../../../services/api-endpoint-helper.service';
import { Component, OnInit } from "@angular/core";
import { ClinicDTO } from '../../../models/ClinicDTO';
import { ApiHttpService } from "../../../../services/api-http.service";
import { FormsModule } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatList, MatListItem } from "@angular/material/list";
import { ActivatedRoute } from "@angular/router";
import { TreatmentDTO } from '../../../models/TreatmentDTO';
import {MatTabsModule} from '@angular/material/tabs';
import { TreatmentListComponent } from "../../treatments/treatment-list/treatment-list.component";
import { MatIconModule } from '@angular/material/icon';
import { MatCardModule } from '@angular/material/card';
import { MatMenuModule } from '@angular/material/menu';
import { MatDividerModule } from '@angular/material/divider';
import { ClinicPersonalComponent } from "../clinic-personal/clinic-personal.component";
import { MatButtonModule } from '@angular/material/button';
import { ClinicService } from '../../../../services/clinic.service';

@Component({
  selector: 'app-clinic-detail',
  templateUrl: './clinic-detail.component.html',
  styleUrl: './clinic-detail.component.scss',
  imports: [FormsModule, MatFormFieldModule, MatInputModule, MatTabsModule, TreatmentListComponent, MatIconModule, MatCardModule,
    MatMenuModule, MatDividerModule, ClinicPersonalComponent, MatButtonModule]
})
export class ClinicDetailComponent implements OnInit {

  clinic: ClinicDTO | undefined = undefined;
  idParam: string = "";
  treatment: TreatmentDTO | undefined = undefined;
  constructor(private route: ActivatedRoute, private api: ApiHttpService,
    private endpointHelper: ApiEndpointHelperService,
    private clinicService: ClinicService) {}

  ngOnInit(): void {
    this.extractId();
    this.findClinic();
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

  createClinic(): void {
    if(this.clinic === undefined) return;
    this.clinicService.createClinic(this.clinic).then(clinic => {
      this.clinic = clinic;
    });
  }

  updateClinic(): void {
    if(this.clinic === undefined) return;
    this.clinicService.updateClinic(Number(this.clinic.id), this.clinic).then(clinic => {
      this.clinic = clinic;
    });
  }

  deleteClinic(): void {
    if(this.clinic === undefined) return;
    this.clinicService.deleteClinic(Number(this.clinic.id)).then(clinic => {
      this.clinic = clinic;
    });
  }
}