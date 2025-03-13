import { Injectable } from '@angular/core';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';
import { ApiHttpService } from './api-http.service';
import { firstValueFrom } from 'rxjs';
import { TreatmentDTO } from '../app/models/TreatmentDTO';

@Injectable({
  providedIn: 'root'
})
export class TreatmentService {

  constructor(private api: ApiHttpService, private apiEndpointHelper: ApiEndpointHelperService) {

  }

  getAllTreatments(): Promise<TreatmentDTO[]>{
    return firstValueFrom(this.api.get('http://localhost:8080/treatments/findAll'));
  }

  getTreatmentById(id: string): Promise<TreatmentDTO>{
    const params: Map<string, any> = new Map<string, any>();
    params.set("id", id);
    return firstValueFrom(this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters("/treatments/findTreatmentById",
    params)));
  }

}
