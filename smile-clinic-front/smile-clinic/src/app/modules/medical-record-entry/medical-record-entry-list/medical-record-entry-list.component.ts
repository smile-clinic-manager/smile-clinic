import { Component, inject, Input, OnInit, ViewChild } from '@angular/core';
import { MedicalHistoryDTO } from '../../../models/MedicalHistoryDTO';
import { MedicalRecordEntriesService } from '../../../../services/medical-record-entries.service';
import { MedicalRecordEntryDTO } from '../../../models/MedicalRecordEntryDTO';
import { SnackbarServiceService } from '../../../../services/snackbar-service.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatExpansionModule } from '@angular/material/expansion';
import { MatDialog } from '@angular/material/dialog';
import { MedicalRecordEntryFormComponent } from '../../../dialogs/medical-record-entry-form/medical-record-entry-form.component';

@Component({
  selector: 'app-medical-record-entry-list',
  imports: [MatTableModule, MatIconModule, MatButtonModule, MatPaginatorModule, MatExpansionModule, MatButtonModule],
  templateUrl: './medical-record-entry-list.component.html',
  styleUrl: './medical-record-entry-list.component.scss'
})
export class MedicalRecordEntryListComponent implements OnInit{

  @Input() medicalHistoryDTO: MedicalHistoryDTO | undefined = undefined;
  dataSource: MatTableDataSource<MedicalRecordEntryDTO> = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  readonly dialog = inject(MatDialog);

  displayedColumns: string[] = ["TRATAMIENTOS"];

  constructor(private medicalRecordEntriesService: MedicalRecordEntriesService, private snackbarService: SnackbarServiceService){  }

  async ngOnInit(): Promise<void> {
    await this.getAllMedicalRecordsByMedicalHistory();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  async getAllMedicalRecordsByMedicalHistory(): Promise<void>{
    this.medicalRecordEntriesService.getAllMedicalRecordsByMedicalHistory(this.medicalHistoryDTO!.id)
      .then((medicalRecords: MedicalRecordEntryDTO[]) => {
        this.dataSource.data = medicalRecords;
      })
      .catch(()=>this.snackbarService.showErrorSnackBar("Error al encontrar la visitas"));
  }

  getFecha(dateTime: string): string{
    return dateTime.split('T')[0]; //Mostramos solo el dia de la fecha en el accordion
  }

  openMedicalRecordEntryFormDialog(): void{
    const dialogRef = this.dialog.open(MedicalRecordEntryFormComponent, {
            data: {
              clinicId: 8, //TODO: Pasar el dato de la clínica activa
              medicalHistoryDTO: this.medicalHistoryDTO
            },
            panelClass: "lateral-dialog"
          });
  }
}   