import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { AppointmentDTO } from '../app/models/AppointmentDTO';
import { firstValueFrom } from 'rxjs';
import { AppointmentComponent } from '../app/modules/appointment/appointment.component';
import { AppointmentFormDTO } from '../app/models/AppointmentFormDTO';

@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  constructor(private api: ApiHttpService,
    private apiEndpointHelper: ApiEndpointHelperService) { }

  getAllAppointmentsFromUserId(userId: string): Promise<AppointmentDTO[]> {
    const params: Map<string, any> = new Map();
    params.set('userId', userId)
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/appointments/findByUserId', params))
    );
  }

  getAllAppointmentsFromPatientId(patientId: string): Promise<AppointmentDTO[]> {
    const params: Map<string, any> = new Map();
    params.set('patientId', patientId)
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/appointments/findByPatientId', params))
    );
  }

  getAllAppointmentsFromClinicId(clinicId: string): Promise<AppointmentDTO[]> {
    const params: Map<string, any> = new Map();
    params.set('clinicId', clinicId)
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/appointments/findByClinicId', params))
    );
  }

  updateAppointment(appointment: AppointmentFormDTO): Promise<AppointmentDTO> {
    console.log('Updating appointment:', appointment);
    return firstValueFrom(
      this.api.put(this.apiEndpointHelper.createUrl('appointments/updateAppointment'), appointment)
    );
  }

  createAppointment(appointment: AppointmentFormDTO): Promise<AppointmentDTO> {
    console.log('Creating appointment:', appointment);
    return firstValueFrom(
      this.api.post(this.apiEndpointHelper.createUrl('appointments/createAppointment'), appointment)
    );
  }

  deleteAppointment(appointmentId: string): Promise<void> {
    const params: Map<string, any> = new Map();
    params.set('appointmentId', appointmentId)
    return firstValueFrom(
      this.api.delete(this.apiEndpointHelper.createUrlWithQueryParameters('/appointments/deleteAppointment', params))
    );
  }

}
