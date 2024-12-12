import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class HttpRequestInterceptorService implements HttpInterceptor{

  constructor() { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const localToken: string = localStorage.getItem('token')?? ''; //token or '' value
    
    if (!request.url.includes('auth/login') && localToken) {
      request = request.clone({headers: request.headers.set('Authorization', localToken)});
    } 

    return next.handle(request);
  }

}
