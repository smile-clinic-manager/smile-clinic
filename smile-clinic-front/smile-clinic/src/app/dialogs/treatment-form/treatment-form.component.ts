import { Component, Inject, OnInit } from '@angular/core';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MAT_DIALOG_DATA, MatDialogRef } from '@angular/material/dialog';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatIconModule } from '@angular/material/icon';
import { MatInputModule } from '@angular/material/input';
import { TreatmentDTO } from '../../models/TreatmentDTO';
import { TreatmentService } from '../../../services/treatment.service';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';

@Component({
  selector: 'app-treatment-form',
  imports: [MatButtonModule, MatCardModule, MatFormFieldModule, FormsModule, ReactiveFormsModule, MatInputModule, MatIconModule],
  templateUrl: './treatment-form.component.html',
  styleUrl: './treatment-form.component.scss'
})
export class TreatmentFormComponent implements OnInit{

  constructor(@Inject(MAT_DIALOG_DATA) public data: {clinicId: string, treatment: TreatmentDTO | null}, 
      private dialogRef: MatDialogRef<TreatmentFormComponent>, private treatmentService: TreatmentService,
      private snackBarService:  SnackbarServiceService){  }

  treatmentForm = new FormGroup({
    name: new FormControl('', Validators.required),
    price: new FormControl('', Validators.required),
    notes: new FormControl('', Validators.required),
  })

  isEditing: boolean = false;

  ngOnInit(): void {
    console.log("data");
    console.log(this.data);
    if (this.data.treatment !== null) {
      this.isEditing = true;
      this.treatmentForm.patchValue({
        ...this.data.treatment,
        price: this.data.treatment.price.toString()
      });
    }
  }

  sendTreatmentForm(): void{
    const treatment: TreatmentDTO = this.getTreatmentFormData();
    if(this.isEditing) this.updateTreatment(treatment);
    else this.createTreatment(treatment);
  }

  createTreatment(treatment: TreatmentDTO){
    this.treatmentService.createTreatment(treatment).then(()=>{
      this.closeDialog();
      this.snackBarService.showSuccessSnackBar("Tratamiento creado");
    }).catch((e)=> this.snackBarService.showErrorSnackBar("Error al crear el tratamiento"))
  }

  updateTreatment(treatment: TreatmentDTO){
    this.treatmentService.updateTreatment(treatment).then(()=>{
      this.closeDialog();
      this.snackBarService.showSuccessSnackBar("Tratamiento editado correctamente");
    }).catch((e)=> this.snackBarService.showErrorSnackBar("Error al crear el tratamiento"))
  }

  closeDialog():void{
    this.dialogRef.close();
  }

  private getTreatmentFormData(): TreatmentDTO {
    return {
      id: this.data.treatment?.id || '',
      name: this.treatmentForm.get('name')?.value || '',
      price: Number(this.treatmentForm.get('price')?.value) || 0,
      notes: this.treatmentForm.get('notes')?.value || ''
    };
  }
}

