import { LocalStorageService } from './../../../services/local-storage.service';
import { CalendarComponent } from './calendar/calendar.component';
import { AppointmentService } from '../../../services/appointment.service';
import { Component, OnInit, inject } from '@angular/core';
import { AppointmentDTO } from '../../models/AppointmentDTO';
import { MatTableModule } from '@angular/material/table';
import { MatIconModule } from '@angular/material/icon';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';
import { CommonModule } from '@angular/common';
import { MatDialog } from '@angular/material/dialog';
import { ClinicFormComponent } from '../clinics/clinic-form/clinic-form.component';
import { AppointmentFormComponent } from '../../dialogs/appointment-form/appointment-form.component';

@Component({
  selector: 'app-appointment',
  imports: [MatTableModule, MatIconModule, MatButtonModule, MatChipsModule, CommonModule],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.scss'
})
export class AppointmentComponent implements OnInit {

  user: any;
  selectedDate: Date | null = null;
  readonly dialog = inject(MatDialog);
  clinicId: string | undefined = undefined;

  displayedColumns: string[] = ['FECHA', 'HORA', 'PACIENTE', 'DENTISTA', 'ESTADO', 'ACCIONES'];
  dataSource: AppointmentDTO[] = [];

  constructor(private appointmentService: AppointmentService,
    private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    this.user = this.localStorageService.getUserData();
    this.clinicId = this.localStorageService.getSelectedGlobalClinic()!.clinicId;
    this.findAppointmentsByUserPermissions();
  }

  onDateSelected(date: Date): void {
    this.selectedDate = date;
  }

  findAppointmentsByUserPermissions(): void {
    if (this.localStorageService.getSelectedGlobalRole()?.name === 'CLINIC_DENTIST') {
      this.findAllByUserId(this.user.id);
    } else {
      const clinicId = this.localStorageService.getSelectedGlobalClinic()?.clinicId;
      this.findAllByClinicId(clinicId!);
    }
  }

  //filterByDate

  async findAllByUserId(userId: string): Promise<void> {
    this.dataSource = await this.appointmentService.getAllAppointmentsFromUserId(userId);
  }

  async findAllByPatientId(patientId: string): Promise<void> {
    this.dataSource = await this.appointmentService.getAllAppointmentsFromPatientId(patientId);
  }

  async findAllByClinicId(clinicId: string): Promise<void> {
    this.dataSource = await this.appointmentService.getAllAppointmentsFromClinicId(clinicId);
  }
  
  openAppointmentForm(appointment: AppointmentDTO): void{

  }

  editAppointment(appointment: AppointmentDTO): void{
    const dialogRef = this.dialog.open(AppointmentFormComponent,
        {
          data: {
            appointment: appointment, 
            clinicId: this.clinicId
          }
        });
    
    dialogRef.afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }  

  createAppointment(): void{
    const dialogRef = this.dialog.open(AppointmentFormComponent,
        {
          data: { 
            clinicId: this.clinicId
          }
        });
    
    dialogRef.afterClosed().subscribe(() => {
      this.ngOnInit();
    });
  }  

  deleteAppointment(appointment: AppointmentDTO): void{
    console.log('delete', appointment);
  }

  getAppointmentDate(appointment: AppointmentDTO): string{
    return appointment.dateTime.split('T')[0];
  }

  getAppointmentTime(appointment: AppointmentDTO): string{
    return appointment.dateTime.split('T')[1];
  }

  getPatientName(appointment: AppointmentDTO): string{
    return `${appointment.patient.firstName} ${appointment.patient.lastName1} ${appointment.patient.lastName2}`;
  }

  getDentistName(appointment: AppointmentDTO): string{
    return `${appointment.user.firstName} ${appointment.user.lastName1} ${appointment.user.lastName2}`;
  }

  getRowClass(appointment: AppointmentDTO): string{
    return 'success-chip';
  }
  
}