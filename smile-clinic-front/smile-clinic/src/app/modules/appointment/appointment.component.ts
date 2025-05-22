import { LocalStorageService } from './../../../services/local-storage.service';
import { CalendarComponent } from './calendar/calendar.component';
import { AppointmentService } from '../../../services/appointment.service';
import { Component, OnInit } from '@angular/core';
import { AppointmentDTO } from '../../models/AppointmentDTO';

@Component({
  selector: 'app-appointment',
  imports: [CalendarComponent],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.scss'
})
export class AppointmentComponent implements OnInit {

  user: any;
  appointments: AppointmentDTO[] = [];

  constructor(private appointmentService: AppointmentService,
    private localStorageService: LocalStorageService,
    private calendar: CalendarComponent) { }

  ngOnInit(): void {
    this.user = this.localStorageService.getUserData(); 
    this.findAppointmentsByUserPermissions();
  }

  findAppointmentsByUserPermissions(): void {
    if (this.user.role === 'CLINIC_DENTIST') {
      this.findAllByUserId(this.user.id);
    } else {
      this.findAllByClinicId(this.user.clinicId);
    }
  }

  //filterByDate

  async findAllByUserId(userId: string): Promise<void> {
    this.appointments = await this.appointmentService.getAllAppointmentsFromUserId(userId);
    console.log(this.appointments);
  }

  async findAllByPatientId(patientId: string): Promise<void> {
    this.appointments = await this.appointmentService.getAllAppointmentsFromPatientId(patientId);
    console.log(this.appointments);
  }

  async findAllByClinicId(clinicId: string): Promise<void> {
    this.appointments = await this.appointmentService.getAllAppointmentsFromClinicId(clinicId);
    console.log(this.appointments);
  }
}