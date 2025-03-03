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
import { TreatmentListComponent } from './modules/treatment-list/treatment-list.component';
import { TreatmentDetailComponent } from './modules/treatment-detail/treatment-detail.component';

export const routes: Routes = [
  { path: 'welcome', component: WelcomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signUp', component: SignUpComponent },
  { path: '', component: LayoutComponent, canActivate: [authGuard],
    children: [
      { path: 'home', component: HomeComponent, canActivate: [authGuard] },
      { path: 'clinic-list', component : ClinicListComponent, canActivate: [authGuard] },
      { path: 'clinic-detail/:id', component : ClinicDetailComponent, canActivate: [authGuard] },
      { path: 'treatment-list', component: TreatmentListComponent },
      { path: 'treatment-detail/:id', component: TreatmentDetailComponent },
    ]
  },
  { path: '**', component: PageNotFoundComponent }, // Wildcard route (404 page)
  { path: '', component: PageNotFoundComponent }, //wildcard, when none route is matched, this one triggers (404 err)
  { path: '**', component: PageNotFoundComponent } // Wildcard route (404 page)
];
