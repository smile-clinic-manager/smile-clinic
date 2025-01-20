import { ApplicationConfig, importProvidersFrom, provideZoneChangeDetection } from '@angular/core';
import { provideRouter } from '@angular/router';

import { routes } from './app.routes';
import { provideClientHydration, withEventReplay } from '@angular/platform-browser';
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';
import { HTTP_INTERCEPTORS, HttpClient, provideHttpClient, withInterceptorsFromDi } from '@angular/common/http';
import { HttpRequestInterceptorService } from '../services/http-request-interceptor.service';

export const appConfig: ApplicationConfig = {
  providers: [provideZoneChangeDetection({ eventCoalescing: true }), 
    provideClientHydration(withEventReplay()), provideAnimationsAsync(), 
    provideRouter(routes), provideHttpClient(withInterceptorsFromDi()), //Allow httpClient interceptors
    {
      provide: HTTP_INTERCEPTORS, 
      useClass: HttpRequestInterceptorService, //Interceptor to append the Authorization header with jwt
      multi: true
    }, provideAnimationsAsync()
  ]
};
