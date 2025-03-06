import { Component, Input } from "@angular/core";
import { MatTableModule } from "@angular/material/table";
import { TreatmentDTO } from "../../models/TreatmentDTO";
import { ApiHttpService } from "../../../services/api-http.service";
import { OnInit } from "@angular/core";

@Component({
  selector: 'app-treatment-list',
  imports : [MatTableModule],
  templateUrl: './treatment-list.component.html',
  styleUrl: './treatment-list.component.scss',
})
export class TreatmentListComponent {
  @Input() clinicId: string | undefined = ''; 

  displayedColumns: string[] = ["name", "notes", "price"];

  dataSource: TreatmentDTO[] = [
    {id: '1', name: "treatment1", notes: "notes1", price: 100},
    {id: '2', name: "treatment2", notes: "notes2", price: 200}
  ];

  constructor(private api: ApiHttpService) {}
}