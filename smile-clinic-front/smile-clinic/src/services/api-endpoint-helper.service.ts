import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ApiEndpointHelperService {

  private static API_ENDPOINT_URL: string = 'http://localhost:8080';

  public createUrl(action: string): string {
    console.log(`${ApiEndpointHelperService.API_ENDPOINT_URL}/${action}`);
    return `${ApiEndpointHelperService.API_ENDPOINT_URL}/${action}`;
  }

  public createUrlWithQueryParameters(action: string, parameters: Map<string, any>): string {
    let paramArr: string[] = [];

    parameters.forEach((value, key) => {
      paramArr.push(`${encodeURIComponent(key)}=${encodeURIComponent(value)}`);
    })
    // Unimos los parametros tal que: 'id=1&nombre=Pablo&ciudad=Sevilla'
    const stringParams: string = paramArr.join("&");

    console.log(`${ApiEndpointHelperService.API_ENDPOINT_URL}/${action}?${stringParams}`);
    return `${ApiEndpointHelperService.API_ENDPOINT_URL}/${action}?${stringParams}`;
  }
  
}
