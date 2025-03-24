import { Component, inject, Injector, Input, ViewChild } from "@angular/core";
import { MatTableDataSource, MatTableModule } from "@angular/material/table";
import { TreatmentDTO } from "../../models/TreatmentDTO";
import { ApiHttpService } from "../../../services/api-http.service";
import { OnInit } from "@angular/core";
import { TreatmentService } from "../../../services/treatment.service";
import { SnackbarServiceService } from "../../../services/snackbar-service.service";
import { MatIconModule } from "@angular/material/icon";
import { MatButtonModule } from "@angular/material/button";
import { MatPaginator, MatPaginatorModule } from "@angular/material/paginator";
import { MatSort, MatSortModule } from "@angular/material/sort";
import { MatDialog, MatDialogRef } from "@angular/material/dialog";
import { DeleteTreatmentComponent } from "../../dialogs/delete-treatment/delete-treatment.component";
import { TreatmentFormComponent } from "../../dialogs/treatment-form/treatment-form.component";

@Component({
  selector: 'app-treatment-list',
  imports : [MatTableModule, MatIconModule, MatButtonModule, MatPaginatorModule, MatSortModule],
  templateUrl: './treatment-list.component.html',
  styleUrl: './treatment-list.component.scss',
})
export class TreatmentListComponent implements OnInit{
  @Input() clinicId: string | undefined = ''; 

  displayedColumns: string[] = ["name", "notes", "price", "acciones"];
  dataSource: MatTableDataSource<TreatmentDTO> = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  readonly dialog = inject(MatDialog);
  

  constructor(private api: ApiHttpService, private treatmentService: TreatmentService, 
    private snackBarService: SnackbarServiceService) {}

  ngOnInit(): void {
    this.getTreatments();
  }

  getTreatments(): void{
    this.treatmentService.getClinicTreatmentList(this.clinicId!).then((treatments: TreatmentDTO[])=>{
      this.dataSource.data = treatments;
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    }).catch((error)=> this.snackBarService.showErrorSnackBar("Error al recuperar los tratamientos"))

  }

  openDeleteTreatmentDialog(treatment: TreatmentDTO): void {
    const dialogRef = this.dialog.open(DeleteTreatmentComponent, {
      data: { 
        treatment: treatment,
      },
        panelClass: 'pop-up-dialog'
    });

    this.updateDataSource(dialogRef);
  }

  openTreatmentFormDialog(treatment?: TreatmentDTO): void {
    const dialogRef = this.dialog.open(TreatmentFormComponent, {
      data: { 
        treatment: treatment ?? null, //cuando estamos creando se pasa valor 'null'
        clinicId: this.clinicId
      },
      panelClass: 'pop-up-treatment-form'
    });

    this.updateDataSource(dialogRef);
  }


  private updateDataSource(dialogRef: MatDialogRef<any, any>) {
    
    dialogRef.afterClosed().subscribe((reload) => {
      if(reload) this.getTreatments(); // reload treatments data
    });
  }


}