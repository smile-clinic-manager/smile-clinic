import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA, MatDialogModule, MatDialogRef } from '@angular/material/dialog';
import { AppointmentDTO } from '../../models/AppointmentDTO';
import { AppointmentService } from '../../../services/appointment.service';
import { SnackbarServiceService } from '../../../services/snackbar-service.service';
import { FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { MatFormFieldModule } from '@angular/material/form-field';
import { CommonModule } from '@angular/common';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule } from '@angular/material/input';
import { MatOptionModule, provideNativeDateAdapter } from '@angular/material/core';
import { MatSelectModule } from '@angular/material/select';
import { MatIconModule } from '@angular/material/icon';
import { MatTimepickerModule } from '@angular/material/timepicker';
import { MatCardModule } from '@angular/material/card';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { userData } from '../../models/userData';
import { UserService } from '../../../services/user.service';
import { LocalStorageService } from '../../../services/local-storage.service';
import { MatButtonModule } from '@angular/material/button';
import { PatientDTO } from '../../models/PatientDTO';
import { PatientService } from '../../../services/patient.service';
import { AppointmentFormDTO } from '../../models/AppointmentFormDTO';

@Component({
  selector: 'app-appointment-form',
  imports: [MatFormFieldModule, ReactiveFormsModule, FormsModule, CommonModule, MatDatepickerModule,
    MatInputModule, MatSelectModule, MatIconModule, MatTimepickerModule, MatCardModule, MatCheckboxModule,
    MatOptionModule, MatDialogModule, MatButtonModule],
  providers:[provideNativeDateAdapter()],
  templateUrl: './appointment-form.component.html',
  styleUrl: './appointment-form.component.scss'
})
export class AppointmentFormComponent implements OnInit{
  
  appointment: AppointmentDTO | undefined = undefined; 
  isCreating: boolean = true;
  users: userData[] = [];
  patients: PatientDTO[] = [];
  clinicId: string = "";

  appointmentForm = new FormGroup({
      date: new FormControl('', [Validators.required]),
      time: new FormControl<Date | null>(null, [Validators.required]),
      patient: new FormControl('', [Validators.required]),
      user: new FormControl('', [Validators.required]),
      state: new FormControl(''),
      visitPurpose: new FormControl(''),
      duration: new FormControl(30, [Validators.required, Validators.pattern('^[0-9]*$')])
    });

  constructor(@Inject(MAT_DIALOG_DATA) public data: {appointment: AppointmentDTO | undefined, clinicId: string}, 
      private dialogRef: MatDialogRef<AppointmentFormComponent>, private appointmentService: AppointmentService,
      private snackBarService: SnackbarServiceService, private userService: UserService, 
      private localStorageService: LocalStorageService, private patientService: PatientService){  }
  
  ngOnInit(): void {
    this.clinicId = this.data.clinicId;
    this.getAllDentistsByClinicId();
    this.getAllPatientsByClinicId();
    this.initializeForm();
  }

  initializeForm() {
    if (this.data.appointment !== undefined) {
      this.appointment = this.data.appointment;
      // TODO: HACER EL ESTADO DE LA CITA
      this.appointmentForm.get('date')?.setValue(this.appointment!.dateTime.split('T')[0]);
      const timeFormatted: Date = this.getTimeFormat(this.appointment!.dateTime.split('T')[1]);
      this.appointmentForm.get('time')?.setValue(timeFormatted);
      this.appointmentForm.get('patient')?.setValue(this.appointment!.patient.id);
      this.appointmentForm.get('user')?.setValue(this.appointment!.user.id);
      this.appointmentForm.get('duration')?.setValue(this.appointment!.duration);
      this.appointmentForm.get('visitPurpose')?.setValue(this.appointment.visitPurpose);

      this.isCreating = false;
    }
  }

  updateForm(appointment: AppointmentDTO){

  }

  getAllDentistsByClinicId(): void{
    this.userService.getAllDentistsByClinicId(this.data.clinicId).then((users: userData[])=>{
      this.users = users;
    })
    .catch(() => this.snackBarService.showErrorSnackBar("Error al recuperar los especialistas"));
  }

  getAllPatientsByClinicId(): void{
    this.patientService.getPatientsByActiveClinicId().then((patients: PatientDTO[])=>{
      this.patients = patients;
    })
    .catch(() => this.snackBarService.showErrorSnackBar("Error al recuperar los pacientes"));
  }
  
  updateAppointment(appointment: AppointmentFormDTO): void{
    this.appointmentService.updateAppointment(appointment)
    .then((appointment) => {
      this.appointment = appointment;
      this.updateForm(appointment);
    })
    .catch(()=> this.snackBarService.showErrorSnackBar('Error al actualizar la cita'));
  }

  createAppointment(appointment: AppointmentFormDTO): void{
    this.appointmentService.createAppointment(appointment)
    .then(() => {
      this.dialogRef.close();
    })
    .catch(()=> this.snackBarService.showErrorSnackBar('Error al crear la cita'));
  }

  requiredFieldErrorMessage(): string {
    return ('Campo obligatorio');
  }

  isValid(): boolean {
    return this.appointmentForm.valid;
  }

  getCompleteName(user: userData){
    return `${user.firstName} ${user.lastName1} ${user.lastName2 ?? ''}`;
  }

  getCompleteNamePatient(patient: PatientDTO){
    return `${patient.firstName} ${patient.lastName1} ${patient.lastName2 ?? ''}`;
  }

  getTimeFormat(timeString: string): Date {
    const [hours, minutes, seconds] = timeString.split(':').map(Number);

    const dateWithTime = new Date();
    dateWithTime.setHours(hours, minutes, seconds || 0, 0);
    return dateWithTime;
  }

  sendAppointmentForm():void{
    if (!this.appointmentForm.valid) {
      this.snackBarService.showErrorSnackBar('Por favor complete todos los campos requeridos');
      return;
    }

    const formValue = this.appointmentForm.value;

    const appointment: AppointmentFormDTO = {
      id: this.appointment?.id ?? '', // or generate a new id if needed
      date: formValue.date ? new Date(formValue.date).toISOString().split('T')[0] : '',
      time: formValue.time ? new Date(formValue.time).toISOString().split('T')[1] : '',
      duration: (formValue?.duration ?? 30).toString(),
      visitPurpose: formValue?.visitPurpose ?? '',
      patientId: formValue.patient!,
      userId: formValue.user!
    };

    if(this.isCreating){
      this.createAppointment(appointment);
    }else {
      this.updateAppointment(appointment);
    }
  }

  blockNonNumeric(event: KeyboardEvent): void{
    const allowed = /^[0-9]$/;
    if (!allowed.test(event.key)) {
      event.preventDefault();
    }
  }
}
