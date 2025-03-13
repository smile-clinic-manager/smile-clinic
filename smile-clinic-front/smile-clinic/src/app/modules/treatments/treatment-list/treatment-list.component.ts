import { Component } from "@angular/core";
import { MatTableModule } from "@angular/material/table";
import { TreatmentDTO } from "../../../models/TreatmentDTO";
import { ApiHttpService } from "../../../../services/api-http.service";
import { OnInit } from "@angular/core";
import { TreatmentService } from "../../../../services/treatment.service";

@Component({
  selector: 'app-treatment-list',
  imports : [MatTableModule],
  templateUrl: './treatment-list.component.html',
  styleUrls: ['./treatment-list.component.scss']
})
export class TreatmentListComponent implements OnInit {

  displayedColumns: string[] = ["name", "notes", "price"];

  dataSource: TreatmentDTO[] = [{id: "0", name: "", notes: "", price: 0}];
  constructor(private treatmentService: TreatmentService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.treatmentService.getAllTreatments()
    .then(treatments => {
      this.dataSource = treatments;
    })
    .catch((error) => console.log(error));
  }
}