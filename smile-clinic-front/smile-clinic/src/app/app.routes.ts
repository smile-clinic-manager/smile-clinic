import { Routes } from '@angular/router';
import { WelcomeComponent } from './modules/welcome/welcome.component';
import { PageNotFoundComponent } from './modules/page-not-found/page-not-found.component';
import { LayoutComponent } from './modules/layout/layout.component';
import { LoginComponent } from './modules/login/login.component';
import { SignUpComponent } from './modules/signup/signup.component';
import { HomeComponent } from './modules/home/home.component';
import { authGuard } from './auth.guard';
import { ClinicListComponent } from './modules/clinic-list/clinic-list.component';
import { ClinicDetailComponent } from './modules/clinic-detail/clinic-detail.component';

export const routes: Routes = [
  { path: 'welcome', component: WelcomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signUp', component: SignUpComponent },
  { path: '', component: LayoutComponent, canActivate: [authGuard],
    children: [
      { path: 'home', component: HomeComponent, canActivate: [authGuard] },
      { path: 'clinic-list', component : ClinicListComponent, canActivate: [authGuard] },
      { path: 'clinic-detail', component : ClinicDetailComponent, canActivate: [authGuard] },
    ]
  },
  { path: '**', component: PageNotFoundComponent } // Wildcard route (404 page)
];
