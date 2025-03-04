import { Component } from "@angular/core";
import { MatTableModule } from "@angular/material/table";
import { TreatmentDTO } from "../../models/TreatmentDTO";
import { ApiHttpService } from "../../../services/api-http.service";
import { OnInit } from "@angular/core";

@Component({
  selector: 'app-treatment-list',
  imports : [MatTableModule],
  templateUrl: './treatment-list.component.html',
  styleUrls: ['./treatment-list.component.scss']
})
export class TreatmentListComponent {


  displayedColumns: string[] = ["name", "notes", "price"];

  dataSource: TreatmentDTO[] = [
    {name: "treatment1", notes: "notes1", price: 100},
    {name: "treatment2", notes: "notes2", price: 200}
  ];

  constructor(private api: ApiHttpService) {}

  }
}