import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { TeethDTO } from '../../models/TeethDTO';
import { TreatmentDTO } from '../../models/TreatmentDTO';
import { TreatmentInstanceDTO } from '../../models/TreatmentInstanceDTO';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-teeth-detail',
  imports: [CommonModule],
  templateUrl: './teeth-detail.component.html',
  styleUrl: './teeth-detail.component.scss'
})
export class TeethDetailComponent implements OnInit{
  teeth: TeethDTO | undefined = undefined;
  treatments: TreatmentInstanceDTO[] | undefined = undefined;

  constructor(@Inject(MAT_DIALOG_DATA) public data: {teeth: TeethDTO}, 
      private dialogRef: MatDialogRef<TeethDetailComponent>){ }

  ngOnInit(): void {
    this.teeth = this.data.teeth;
    this.treatments = this.teeth.medicalRecords.map(record => record.treatmentInstance);
    console.log(this.treatments)
  }

}
