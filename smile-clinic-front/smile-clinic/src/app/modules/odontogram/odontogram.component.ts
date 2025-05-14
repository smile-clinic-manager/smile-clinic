import { Component, Input, OnInit } from '@angular/core';
import { TeethDTO } from '../../models/TeethDTO';
import { OdontogramService } from '../../../services/odontogram.service';
import { MedicalHistoryDTO } from '../../models/MedicalHistoryDTO';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { MatCard, MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-odontogram',
  imports: [MatCardModule, CommonModule],
  templateUrl: './odontogram.component.html',
  styleUrl: './odontogram.component.scss'
})
export class OdontogramComponent implements OnInit{
  @Input() medicalHistoryDTO: MedicalHistoryDTO | undefined = undefined;
  teethList: TeethDTO[] = [];

  rows: TeethDTO[][] = [];

  constructor(private odontogramService :OdontogramService, private snackBarService: SnackbarServiceService){}

  async ngOnInit(): Promise<void> {
    await this.getOdontogramData();

    
  }

  async getOdontogramData(): Promise<void>{
    this.odontogramService.getAllTeeth(this.medicalHistoryDTO!.id)
      .then((teethList)=>{
        this.teethList = teethList;
        console.log('this.teethList');
        console.log(this.teethList);
      })
      .finally(() => this.buildOdontogramLayout())
      .catch(e=>this.snackBarService.showErrorSnackBar("Error al obtener el odontograma"));
  }

  buildOdontogramLayout(): void{
    const teethRowLength = 16;
    for (let i = 0; i < this.teethList.length; i += teethRowLength) {
      this.rows.push(this.teethList.slice(i, i + teethRowLength));
    }
    console.log('this.rows');
    console.log(this.rows);
  }

}
