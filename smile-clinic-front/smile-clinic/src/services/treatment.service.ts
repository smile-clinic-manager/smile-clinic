import { Injectable } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { TreatmentDTO } from '../app/models/TreatmentDTO';
import { firstValueFrom } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class TreatmentService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService) { }

  getClinicTreatmentList(clinicId: string): Promise<TreatmentDTO[]>{
    const params: Map<string, any>  = new Map();
    params.set('clinicId', clinicId)
    return firstValueFrom(
      this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/treatments/findTreatmentsByClinicId', params))
    );
  }

  deleteTreatment(treatmentId: string): Promise<void> {
    const params: Map<string, any>  = new Map();
      params.set('treatmentId', treatmentId)
      return firstValueFrom(
        this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/treatments/deleteTreatment', params))
      )
  }

  createTreatment(treatmentDTO: TreatmentDTO): Promise<TreatmentDTO> {
      return firstValueFrom(
        this.api.post(this.apiEndpointHelper.createUrl('treatments/create'), treatmentDTO)
      )
  }

  updateTreatment(treatmentDTO: TreatmentDTO): Promise<TreatmentDTO> {
      return firstValueFrom(
        this.api.put(this.apiEndpointHelper.createUrl('treatments/update'), treatmentDTO)
      )
  }
}
