import { ApiEndpointHelperService } from './../../../services/api-endpoint-helper.service';
import { Component, OnInit } from "@angular/core";
import { ClinicDTO } from './../../models/ClinicDTO';
import { ApiHttpService } from "../../../services/api-http.service";
import { FormsModule } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatList, MatListItem } from "@angular/material/list";
import { ActivatedRoute } from "@angular/router";
import { TreatmentDTO } from '../../models/TreatmentDTO';
import { ApiIdFinderService } from '../../../services/api-id-finder.service';

@Component({
  selector: 'app-clinic-detail',
  templateUrl: './clinic-detail.component.html',
  styleUrl: './clinic-detail.component.scss',
  imports : [FormsModule, MatFormFieldModule, MatInputModule,
    MatList, MatListItem]
})
export class ClinicDetailComponent implements OnInit {

  clinic: ClinicDTO | undefined = undefined;
  idParam: Number | undefined = undefined;
  treatment: TreatmentDTO | undefined = undefined;
  constructor(private route: ActivatedRoute, private api: ApiHttpService,
    private endpointHelper: ApiEndpointHelperService,
    private finder: ApiIdFinderService) {}

  ngOnInit(): void {
    this.clinic = this.finder.getClinic() as ClinicDTO;
    //console.log(this.clinic);
  }

  /*findClinic(): void {
    const params: Map<string, any> = new Map<string, any>();
    params.set("id", this.idParam);
    this.api.get(this.endpointHelper.createUrlWithQueryParameters("/clinics/findClinicById",
    params)).subscribe((clinic: ClinicDTO) => {
      this.clinic = clinic;
    });
  }

  extractId(): number {
    return Number(this.route.params.subscribe(params => {
      this.idParam = params['id'];
    }));
  }*/
}