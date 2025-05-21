import { Component, Input, OnInit, ViewChild } from '@angular/core';
import { MedicalHistoryDTO } from '../../../models/MedicalHistoryDTO';
import { MedicalRecordEntriesService } from '../../../../services/medical-record-entries.service';
import { MedicalRecordEntryDTO } from '../../../models/MedicalRecordEntryDTO';
import { SnackbarServiceService } from '../../../../services/snackbar-service.service';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { MatTableDataSource, MatTableModule } from '@angular/material/table';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatExpansionModule } from '@angular/material/expansion';

@Component({
  selector: 'app-medical-record-entry-list',
  imports: [MatTableModule, MatIconModule, MatButtonModule, MatPaginatorModule, MatExpansionModule],
  templateUrl: './medical-record-entry-list.component.html',
  styleUrl: './medical-record-entry-list.component.scss'
})
export class MedicalRecordEntryListComponent implements OnInit{
  @Input() medicalHistoryDTO: MedicalHistoryDTO | undefined = undefined;
  dataSource: MatTableDataSource<MedicalRecordEntryDTO> = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator!: MatPaginator;

  displayedColumns: string[] = ["VISITA", "ACCIONES"];

  constructor(private medicalRecordEntriesService: MedicalRecordEntriesService, private snackbarService: SnackbarServiceService){  }

  async ngOnInit(): Promise<void> {
    await this.getAllMedicalRecordsByMedicalHistory();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  async getAllMedicalRecordsByMedicalHistory(): Promise<void>{
    this.medicalRecordEntriesService.getAllMedicalRecordsByMedicalHistory(this.medicalHistoryDTO!.id)
      .then((medicalRecords: MedicalRecordEntryDTO[])=>{
        this.dataSource.data = medicalRecords;
        console.log('gholalalalal');
        console.log(this.dataSource.data);
      })
      .catch(()=>this.snackbarService.showErrorSnackBar("Error al encontrar la visitas"));
  }

  getFecha(dateTime: string): string{
    return dateTime.split('T')[0]; //Mostramos solo el dia de la fecha en el accordion
  }
}
