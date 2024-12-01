import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { HttpClientModule } from '@angular/common/http';

@Injectable({
  providedIn: 'root',
})
export class ApiService {
  private urlApi = 'http://localhost:8080/students/findAll';

  constructor(private http: HttpClient) {}

  public getData(): Observable<any> {
    return this.http.get(this.urlApi);
  }
}
