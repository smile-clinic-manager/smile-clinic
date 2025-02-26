import { Component } from "@angular/core";
import { TreatmentDTO } from "../../models/TreatmentDTO";
import { MatFormField, MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { FormsModule } from "@angular/forms";

@Component({
  selector: 'app-treatment-detail',
  templateUrl: './treatment-detail.component.html',
  styleUrls: ['./treatment-detail.component.scss'],
  imports: [FormsModule, MatFormFieldModule, MatFormFieldModule, MatInputModule]
})
export class TreatmentDetailComponent {
  treatment: TreatmentDTO = {name: "treatment1", notes: "notes1", price: 100};
}