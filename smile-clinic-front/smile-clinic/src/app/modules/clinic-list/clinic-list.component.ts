import { ClinicDTO } from './../../models/ClinicDTO';
import { ApiHttpService } from './../../../services/api-http.service';
import { Component, OnInit } from "@angular/core";
import { MatTableModule } from "@angular/material/table";

@Component({
  selector: 'app-clinic-list',
  imports: [MatTableModule],
  templateUrl: './clinic-list.component.html',
  styleUrl: './clinic-list.component.scss'
})
export class ClinicListComponent implements OnInit {

    displayedColumns: string[] = ["name", "postalCode", "address", "phoneNumber", "email"];
    dataSource: ClinicDTO[] = [];

  constructor(private api: ApiHttpService) {}

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.api.get("http://localhost:8080/clinics/findAll").subscribe((clinics: ClinicDTO[]) => {
      this.dataSource = clinics;
    });
  }
}

