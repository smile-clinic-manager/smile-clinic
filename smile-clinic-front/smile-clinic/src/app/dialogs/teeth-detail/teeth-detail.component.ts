import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TeethDTO } from '../../models/TeethDTO';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { TreatmentInstanceDTO } from '../../models/TreatmentInstanceDTO';
import { CommonModule } from '@angular/common';
import { MedicalRecordEntryDTO } from '../../models/MedicalRecordEntryDTO';
import { MatExpansionModule } from '@angular/material/expansion';

@Component({
  selector: 'app-teeth-detail',
  imports: [CommonModule, MatExpansionModule, MatProgressBarModule],
  templateUrl: './teeth-detail.component.html',
  styleUrl: './teeth-detail.component.scss'
})
export class TeethDetailComponent implements OnInit{
  teeth: TeethDTO | undefined = undefined;
  medicalRecords: MedicalRecordEntryDTO[] | undefined = undefined;
  treatments: TreatmentInstanceDTO[] | undefined = undefined;

  constructor(@Inject(MAT_DIALOG_DATA) public data: {teeth: TeethDTO}, 
      private dialogRef: MatDialogRef<TeethDetailComponent>){ }

  ngOnInit(): void {
    this.teeth = this.data.teeth;
    this.medicalRecords = this.teeth.medicalRecords.sort((a, b) => new Date(b.dateTime).getTime() - new Date(a.dateTime).getTime());
    this.treatments = this.teeth.medicalRecords.map(record => record.treatmentInstance);
    console.log("RECROOOORODO");
    console.log(this.medicalRecords);
  }
  
  getFecha(dateTime: string): string{
    return dateTime.split('T')[0]; //Mostramos solo el dia de la fecha en el accordion
  }

}
