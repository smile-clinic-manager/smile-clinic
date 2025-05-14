import { Component, Input, OnInit } from '@angular/core';
import { TeethDTO } from '../../models/TeethDTO';
import { OdontogramService } from '../../../services/odontogram.service';
import { MedicalHistoryDTO } from '../../models/MedicalHistoryDTO';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';

@Component({
  selector: 'app-odontogram',
  imports: [],
  templateUrl: './odontogram.component.html',
  styleUrl: './odontogram.component.scss'
})
export class OdontogramComponent implements OnInit{
  @Input() medicalHistoryDTO: MedicalHistoryDTO | undefined = undefined;
  teethList: TeethDTO[] = [];

  constructor(private odontogramService :OdontogramService, private snackBarService: SnackbarServiceService){}

  ngOnInit(): void {
    console.log(this.medicalHistoryDTO);
    this.odontogramService.getAllTeeth(this.medicalHistoryDTO!.id)
      .then((teethList)=>{
        this.teethList = teethList;
        console.log(this.teethList);
      })
      .catch(e=>this.snackBarService.showErrorSnackBar("Error al obtener el odontograma"));
  }
}
