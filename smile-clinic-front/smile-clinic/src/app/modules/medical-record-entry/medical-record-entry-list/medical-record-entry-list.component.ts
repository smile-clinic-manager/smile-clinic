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
import { LocalStorageService } from '../../../../services/local-storage.service';
import { MatDivider } from '@angular/material/divider';
import { MatMenuModule } from '@angular/material/menu';

@Component({
  selector: 'app-medical-record-entry-list',
  imports: [MatTableModule, MatIconModule, MatButtonModule, MatPaginatorModule, MatExpansionModule, 
  MatButtonModule, MatDivider, MatMenuModule],
  templateUrl: './medical-record-entry-list.component.html',
  styleUrl: './medical-record-entry-list.component.scss'
})
export class MedicalRecordEntryListComponent implements OnInit{

  @Input() medicalHistoryDTO: MedicalHistoryDTO | undefined = undefined;
  dataSource: MatTableDataSource<MedicalRecordEntryDTO> = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  readonly dialog = inject(MatDialog);
  clinicId: string = "";

  displayedColumns: string[] = ["TRATAMIENTOS"];

  constructor(private medicalRecordEntriesService: MedicalRecordEntriesService, private snackbarService: SnackbarServiceService,
    private localStorageService: LocalStorageService
  ){  }

  async ngOnInit(): Promise<void> {
    this.getActiveClinicId();
    await this.getAllMedicalRecordsByMedicalHistory();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
  }

  getActiveClinicId(): void{
    this.clinicId = this.localStorageService.getSelectedGlobalClinic()!.clinicId;
  }

  async getAllMedicalRecordsByMedicalHistory(): Promise<void>{
    this.medicalRecordEntriesService.getAllMedicalRecordsByMedicalHistory(this.medicalHistoryDTO!.id)
      .then((medicalRecords: MedicalRecordEntryDTO[]) => {
        this.dataSource.data = medicalRecords;
        console.log(this.dataSource.data);
        console.log(this.dataSource.data.length);
      })
      .catch(()=>this.snackbarService.showErrorSnackBar("Error al encontrar la visitas"));
  }

  getFecha(dateTime: string): string{
    return dateTime.split('T')[0]; //Mostramos solo el dia de la fecha en el accordion
  }

  openMedicalRecordEntryFormDialog(element?: MedicalRecordEntryDTO): void{
    const dialogRef = this.dialog.open(MedicalRecordEntryFormComponent, {
            data: {
              clinicId: this.clinicId, //TODO: Pasar el dato de la clínica activa
              medicalHistoryDTO: this.medicalHistoryDTO,
              medicalRecordEntry: element
            },
            panelClass: "wide-lateral-dialog"
          });
    dialogRef.afterClosed().subscribe(()=>{
      this.ngOnInit();
    });
  }

  deleteMedicalRecordEntry(medicalRecordEntry: MedicalRecordEntryDTO): void {
    this.medicalRecordEntriesService.deleteMedicalRecord(medicalRecordEntry.id)
      .then(() => {
        this.ngOnInit();
      })
      .catch(()=>this.snackbarService.showErrorSnackBar("Error al eliminar elemento"))
      .finally(()=> this.snackbarService.showSuccessSnackBar("Elementp borrado con éxito"));
  }


}   