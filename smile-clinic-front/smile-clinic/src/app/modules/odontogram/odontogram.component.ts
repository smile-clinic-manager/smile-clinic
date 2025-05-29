import { Component, inject, Inject, Input, OnInit } from '@angular/core';
import { TeethDTO } from '../../models/TeethDTO';
import { OdontogramService } from '../../../services/odontogram.service';
import { MedicalHistoryDTO } from '../../models/MedicalHistoryDTO';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { MatCard, MatCardModule } from '@angular/material/card';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { TeethDetailComponent } from '../../dialogs/teeth-detail/teeth-detail.component';

@Component({
  selector: 'app-odontogram',
  imports: [MatCardModule, CommonModule],
  templateUrl: './odontogram.component.html',
  styleUrl: './odontogram.component.scss'
})
export class OdontogramComponent implements OnInit{
  @Input() medicalHistoryDTO: MedicalHistoryDTO | undefined = undefined;
  teethList: TeethDTO[] = [];
  rows: any = [];
  readonly dialog = inject(MatDialog);

  constructor(private odontogramService :OdontogramService, private snackBarService: SnackbarServiceService){  }

  async ngOnInit(): Promise<void> {
    await this.getOdontogramData();    
  }

  async getOdontogramData(): Promise<void>{
    this.odontogramService.getAllTeeth(this.medicalHistoryDTO!.id)
      .then((teethList)=>{
        this.teethList = teethList;
      })
      .finally(() => this.buildOdontogramLayout())
      .catch(e=>this.snackBarService.showErrorSnackBar("Error al obtener el odontograma"));
  }

  buildOdontogramLayout(): void{
    // Divimos las piezas en 2 filas para su correcto display en el front
    const teethRowLength = 16;
    for (let i = 0; i < this.teethList.length; i += teethRowLength) {
      this.rows.push(this.teethList.slice(i, i + teethRowLength));
    }

    // Ordenamos bien las piezas dentales
    for(let i = 0; i < this.rows.length; i++){
      let leftTeethQuarter = this.rows[i].slice(0, 8); // Cogemos los dientes del cuadrante izq ya que están mal ordenados
      leftTeethQuarter = leftTeethQuarter.reverse(); // Invertimos y sustituimos en la lista
      this.rows[i] = [...leftTeethQuarter, ...this.rows[i].slice(8)];
    }
  }

  openTeethDetailDialog(teeth: TeethDTO): void{
    const dialogRef = this.dialog.open(TeethDetailComponent, {
      data:{ teeth: teeth},
      panelClass: 'lateral-dialog'
    });

    //dialogRef.afterClosed().subscribe(()=> );

  }
}
