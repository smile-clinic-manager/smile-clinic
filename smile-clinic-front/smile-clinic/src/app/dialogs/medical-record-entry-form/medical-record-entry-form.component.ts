import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { MedicalHistoryDTO } from '../../models/MedicalHistoryDTO';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { MedicalRecordEntriesService } from '../../../services/medical-record-entries.service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MedicalRecordEntryDTO } from '../../models/MedicalRecordEntryDTO';
import { UserService } from '../../../services/user.service';
import { TreatmentService } from '../../../services/treatment.service';
import { userData } from '../../models/userData';
import { TreatmentDTO } from '../../models/TreatmentDTO';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatOptionModule } from '@angular/material/core';
import { MatButtonModule } from '@angular/material/button';
import { MatSelectModule } from '@angular/material/select';
import { CommonModule } from '@angular/common';
import { MatIconModule } from '@angular/material/icon';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {provideNativeDateAdapter} from '@angular/material/core';
import {MatTimepickerModule} from '@angular/material/timepicker';
import { TeethDTO } from '../../models/TeethDTO';
import { OdontogramService } from '../../../services/odontogram.service';
import { MatCardModule } from '@angular/material/card';
import { MatCheckbox, MatCheckboxModule } from '@angular/material/checkbox';
import { MedicalRecordEntryFormDTO } from '../../models/MedicalRecordEntryFormDTO';

@Component({
  selector: 'app-medical-record-entry-form',
  imports: [MatDialogModule, MatButtonModule, MatDialogModule, MatFormFieldModule, MatInputModule, FormsModule,
    ReactiveFormsModule, MatOptionModule, MatSelectModule, MatIconModule, CommonModule, MatDatepickerModule, 
    MatTimepickerModule, MatCardModule, MatCheckboxModule
  ],
  templateUrl: './medical-record-entry-form.component.html',
  providers:[provideNativeDateAdapter()],
  styleUrl: './medical-record-entry-form.component.scss'
})
export class MedicalRecordEntryFormComponent implements OnInit {
  isCreating: boolean = true;
  users: userData[] = [];
  treatments: TreatmentDTO[] = [];
  teethList: TeethDTO[] = [];
  rows: any = [];

  medicalRecordEntryForm = new FormGroup({
    date: new FormControl('', [Validators.required]),
    time: new FormControl('', [Validators.required]),
    observations: new FormControl(''),
    treatment: new FormControl<string>('', [Validators.required]),
    user: new FormControl('', [Validators.required]),
    teeth: new FormControl<string[]>([], [Validators.required]), // Use string[] or number[] as appropriate
  });

  constructor(private dialogRef: MatDialogRef<MedicalRecordEntryFormComponent>,
      @Inject(MAT_DIALOG_DATA) public data: { clinicId: string, medicalHistoryDTO: MedicalHistoryDTO, medicalRecordEntry: MedicalRecordEntryDTO },
      private snackBarService: SnackbarServiceService, private medicalRecordEntriesService: MedicalRecordEntriesService,
      private userService: UserService, private treatmentService: TreatmentService, private odontogramService: OdontogramService
    ){ }

  ngOnInit(): void {
    this.getAllTeeth();
    this.getAllDentistsByClinicId();
    this.getAllTreatmentsByClinicId();
    this.initializeMedicalRecordEntryForm()
  }

  initializeMedicalRecordEntryForm() {
    if (this.data.medicalRecordEntry) {
      this.medicalRecordEntryForm.get('date')?.setValue("");
      this.medicalRecordEntryForm.get('time')?.setValue("");
      this.medicalRecordEntryForm.get('observations')?.setValue(this.data.medicalRecordEntry.observations);
      this.medicalRecordEntryForm.get('treatment')?.setValue(this.data.medicalRecordEntry.treatmentInstance.id);
      this.medicalRecordEntryForm.get('user')?.setValue(this.data.medicalRecordEntry.user.id);

      this.isCreating = false;
    }
  }

  getAllDentistsByClinicId():void{
    this.userService.getAllDentistsByClinicId(this.data.clinicId).then((users: userData[])=>{
      this.users = users;
    })
    .catch(() => this.snackBarService.showErrorSnackBar("Error al recuperar los especialistas"));
  }

  getAllTreatmentsByClinicId(): void{
    this.treatmentService.getClinicTreatmentList(this.data.clinicId).then((treatments: TreatmentDTO[])=>{
      this.treatments = treatments;
    })
    .catch(() => this.snackBarService.showErrorSnackBar("Error al recuperar los tratamientos"));
  }

  getAllTeeth(): void{
    this.odontogramService.getToothEntities().then((teeth: TeethDTO[])=>{
      this.teethList = teeth;
    })
    .finally(()=>this.buildOdontogramLayout())
    .catch(()=> this.snackBarService.showErrorSnackBar("Error al recuperar las piezas dentales"))
  }

  getCompleteName(user: userData){
    return `${user.firstName} ${user.lastName1} ${user.lastName2 ?? ''}`;
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

  toggleSelectTooth(selected: boolean, eventTeeth: TeethDTO):void{
    let selectedTeeth = this.medicalRecordEntryForm.get('teeth')?.value || [];
    if(!selected){
      selectedTeeth = selectedTeeth.filter(t => t !== eventTeeth.id); //Quitamos si se ha deseleccionado
    } else{
      selectedTeeth = [...selectedTeeth, eventTeeth.id];
    }
    
    this.medicalRecordEntryForm.patchValue({...this.medicalRecordEntryForm.value, teeth: selectedTeeth});
  }
  
  isValid(): boolean {
    return this.medicalRecordEntryForm.valid;
  }

  showMsg(){
    this.snackBarService.showSuccessSnackBar("CHECK!")
  }

  requiredFieldErrorMessage(): string {
    return ('Campo obligatorio');
  }

  sendMedicalRecordEntryForm(): void {
    const dateValue = this.medicalRecordEntryForm.get('date')?.value;
    const timeValue = this.medicalRecordEntryForm.get('time')?.value;

    const form: MedicalRecordEntryFormDTO = {
      date: dateValue ? new Date(dateValue).toISOString().split('T')[0] : '', //Cogemos solo la fecha
      time: timeValue ? new Date(timeValue).toISOString().split('T')[1].substring(0, 5) : '', //Cogemos solo la hora

      treatmentId: this.medicalRecordEntryForm.get('treatment')?.value!,
      userId: this.medicalRecordEntryForm.get('dentist')?.value!,
      observations: this.medicalRecordEntryForm.get('observations')?.value!,
      teethListId: this.medicalRecordEntryForm.get('teeth')?.value!
    };

    this.medicalRecordEntriesService.createNewMedicalRecordEntry(form).then(()=>{
      this.dialogRef.close();
    })
    .catch(()=> this.snackBarService.showErrorSnackBar("Error al crear el tratamiento"));
  }
 
}
