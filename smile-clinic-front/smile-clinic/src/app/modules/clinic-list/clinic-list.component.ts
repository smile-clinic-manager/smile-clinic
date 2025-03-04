import { ClinicDTO } from './../../models/ClinicDTO';
import { ApiHttpService } from './../../../services/api-http.service';
import { Component, OnInit } from "@angular/core";
import { MatTableModule } from "@angular/material/table";
import { Router } from '@angular/router';
import { ClinicService } from '../../../services/clinic.service';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { LocalStorageService } from '../../../services/local-storage.service';

@Component({
  selector: 'app-clinic-list',
  imports: [MatTableModule],
  templateUrl: './clinic-list.component.html',
  styleUrl: './clinic-list.component.scss'
})
export class ClinicListComponent implements OnInit {

  displayedColumns: string[] = ["name", "postalCode", "address", "phoneNumber", "email"];
  dataSource: ClinicDTO[] = [];
  user: any = null; //TEMPORARY

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


}

