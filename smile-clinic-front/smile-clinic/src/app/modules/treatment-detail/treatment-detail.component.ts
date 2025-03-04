import { Component, OnInit } from "@angular/core";
import { TreatmentDTO } from "../../models/TreatmentDTO";
import { MatFormField, MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { FormsModule } from "@angular/forms";
import { ActivatedRoute } from "@angular/router";
import { ApiEndpointHelperService } from "../../../services/api-endpoint-helper.service";
import { ApiHttpService } from "../../../services/api-http.service";

@Component({
  selector: 'app-treatment-detail',
  templateUrl: './treatment-detail.component.html',
  styleUrls: ['./treatment-detail.component.scss'],
  imports: [FormsModule, MatFormFieldModule, MatFormFieldModule, MatInputModule]
})
export class TreatmentDetailComponent implements OnInit {

  dataSource: TreatmentDTO = {name: "", notes: "", price: 0};
  id: Number = 0;

  constructor(private route: ActivatedRoute, private api: ApiHttpService,
    private endpointHelper: ApiEndpointHelperService) {}

      ngOnInit(): void {
    this.extractId();
    this.findTreatment();
  }

  findTreatment(): void {
    const params: Map<string, any> = new Map<string, any>();
    params.set("id", this.id);
    this.api.get(this.endpointHelper.createUrlWithQueryParameters("/treatments/findTreatmentById",
    params)).subscribe((treatment: TreatmentDTO) => {
      this.dataSource = treatment;
    });
  }

  extractId(): number {
    return Number(this.route.params.subscribe(params => {
      this.id = params['id'];
    }));
  }
}