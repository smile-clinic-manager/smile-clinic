import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { catchError, Observable, throwError } from 'rxjs';
import { LocalStorageService } from './local-storage.service';
import { Router } from '@angular/router';


@Injectable({
  providedIn: 'root'
})
export class HttpRequestInterceptorService implements HttpInterceptor {

  constructor(private localStorageService: LocalStorageService, private router: Router) { }

  intercept(request: HttpRequest<unknown>, next: HttpHandler): Observable<HttpEvent<unknown>> {
    const localToken: string = this.localStorageService.getTokenInLocalStorage()?? ''; //token or '' value
    
    // login and signup endpoints are not required to be authenticated
    if (!request.url.includes('auth/login') && !request.url.includes('users/register') && localToken) {
      request = request.clone({headers: request.headers.set('Authorization', localToken)});
    } 

    // If user tries to access any endpoints without correct authorization it will redirect to the welcome page
    return next.handle(request).pipe(
      catchError((error: any) => {
        if(error.status === 401) {// unathorized request = 401 code
          this.router.navigate(['welcome']);
        }
        return throwError(() => error);
      })
    );
  }

}
