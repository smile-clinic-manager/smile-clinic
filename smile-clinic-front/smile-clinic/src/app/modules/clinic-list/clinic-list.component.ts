import { ClinicDTO } from './../../models/ClinicDTO';
import { ApiHttpService } from './../../../services/api-http.service';
import { Component, OnInit } from "@angular/core";
import { MatTableModule } from "@angular/material/table";
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';

@Component({
  selector: 'app-clinic-list',
  imports: [MatTableModule, MatIconModule, MatButtonModule],
  templateUrl: './clinic-list.component.html',
  styleUrl: './clinic-list.component.scss'
})
export class ClinicListComponent implements OnInit {

  displayedColumns: string[] = ["NOMBRE", "C. POSTAL", "DIRECCIÓN", "Nº CONTACTO", "EMAIL", "ACCIONES"];
  dataSource: ClinicDTO[] = [];

  constructor(private api: ApiHttpService, private router: Router) {  }

  ngOnInit(): void {
    this.findAll();
  }

  findAll(): void {
    this.api.get("http://localhost:8080/clinics/findAll").subscribe((clinics: ClinicDTO[]) => {
      this.dataSource = clinics;
    });
  }

  viewClinic(id: string | undefined): void {
    if (id) this.router.navigate(['clinic-detail', id]);
  }

}

