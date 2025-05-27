import { LocalStorageService } from './../../../services/local-storage.service';
import { CalendarComponent } from './calendar/calendar.component';
import { AppointmentService } from '../../../services/appointment.service';
import { Component, OnInit, inject } from '@angular/core';
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
  selectedDate: Date | null = null;

  constructor(private appointmentService: AppointmentService,
    private localStorageService: LocalStorageService) { }

  ngOnInit(): void {
    this.user = this.localStorageService.getUserData();
    console.log('User data:', this.user);
    this.findAppointmentsByUserPermissions();
  }

  onDateSelected(date: Date): void {
    this.selectedDate = date;
    console.log('Selected date:', this.selectedDate);
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