import { Component, OnInit } from '@angular/core';
import { AppointmentService } from '../../../services/appointment.service';

@Component({
  selector: 'app-appointment',
  imports: [],
  templateUrl: './appointment.component.html',
  styleUrl: './appointment.component.scss'
})
export class AppointmentComponent implements OnInit {

  constructor(private appointmentService: AppointmentService,) { }

  ngOnInit(): void {
    this.findAllByUserId('X');
  }

  findAllByUserId(userId: string): void {
    this.appointmentService.getAllAppointmentsFromUserId(userId)
      .then(appointments => {
        console.log(appointments);
      })
      .catch((error) => console.error(error))
  }

}
