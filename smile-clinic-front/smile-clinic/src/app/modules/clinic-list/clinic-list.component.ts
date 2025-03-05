import { ClinicDTO } from './../../models/ClinicDTO';
import { ApiHttpService } from './../../../services/api-http.service';
import { Component, OnInit } from "@angular/core";
import { MatTableModule } from "@angular/material/table";
import { MatIconModule } from '@angular/material/icon';
import { Router } from '@angular/router';
import { MatButtonModule } from '@angular/material/button';
import { ClinicService } from '../../../services/clinic.service';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { LocalStorageService } from '../../../services/local-storage.service';

@Component({
  selector: 'app-clinic-list',
  imports: [MatTableModule, MatIconModule, MatButtonModule],
  templateUrl: './clinic-list.component.html',
  styleUrl: './clinic-list.component.scss'
})
export class ClinicListComponent implements OnInit {

  displayedColumns: string[] = ["NOMBRE", "C. POSTAL", "DIRECCIÓN", "Nº CONTACTO", "EMAIL", "ACCIONES"];
  dataSource: ClinicDTO[] = [];
  user: any; //TEMPORALMENTE

  constructor(private api: ApiHttpService, private router: Router, 
    private clinicService: ClinicService, private snackBarService: SnackbarServiceService,
    private localStorageService: LocalStorageService) {}

  ngOnInit(): void {
    this.user = {'id': 1};
    this.findAll();
  }

  findAll(): void {
    this.clinicService.getAllClinics(this.user.id).subscribe({
      next: (clinics: ClinicDTO[]) => {
          this.dataSource = clinics;
      },
      error: (error) => this.snackBarService.showErrorSnackBar(error)
    })
  };

  viewClinic(id : string){
    if(id!==null || id === undefined) this.router.navigate(['clinic-details', id]);
  }

}

