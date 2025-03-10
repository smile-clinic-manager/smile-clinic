import { Injectable, OnInit } from '@angular/core';
import { ApiHttpService } from './api-http.service';
import { ActivatedRoute } from '@angular/router';
import { ApiEndpointHelperService } from './api-endpoint-helper.service';

@Injectable({
  providedIn: 'root'
})
export class ApiIdFinderService implements OnInit {

  clinic: any | undefined = undefined;
  idParam: Number | undefined = undefined;
  treatment: any | undefined = undefined;
  constructor(private route: ActivatedRoute, private api: ApiHttpService,
    private endpointHelper: ApiEndpointHelperService) {}

  ngOnInit(): void {
    this.extractId();
    this.findClinic();
  }

  getClinic(): any {
    return this.clinic;
  }

  findClinic(): void {
    const params: Map<string, any> = new Map<string, any>();
    params.set("id", this.idParam);
    this.api.get(this.endpointHelper.createUrlWithQueryParameters("/clinics/findClinicById",
    params)).subscribe((clinic: any) => {
      this.clinic = clinic;
    });
  }

  extractId(): number {
    return Number(this.route.params.subscribe(params => {
      this.idParam = params['id'];
    }));
  }
}
