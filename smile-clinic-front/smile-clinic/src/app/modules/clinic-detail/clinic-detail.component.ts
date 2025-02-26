import { Component } from "@angular/core";
import { ClinicDTO } from './../../models/ClinicDTO';
import { ApiHttpService } from "../../../services/api-http.service";
import { FormsModule } from "@angular/forms";
import { MatFormFieldModule } from "@angular/material/form-field";
import { MatInputModule } from "@angular/material/input";
import { MatList, MatListItem } from "@angular/material/list";

@Component({
  selector: 'app-clinic-detail',
  templateUrl: './clinic-detail.component.html',
  styleUrl: './clinic-detail.component.scss',
  imports : [FormsModule, MatFormFieldModule, MatInputModule,
    MatList, MatListItem]
})
export class ClinicDetailComponent {

  clinic: ClinicDTO= {
      name: "Lorem Ipsum",
      postalCode: "99282",
      address: "Lorem Ipsum St. 123",
      phoneNumber: "123456789",
      email: "lorem@ipsum.es",
      img: "img.jpg",
      invitations: [],
      treatments: ["treatment1", "treatment2"]
    }
}