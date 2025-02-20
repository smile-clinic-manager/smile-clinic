import { Routes } from '@angular/router';
import { WelcomeComponent } from './modules/welcome/welcome.component';
import { PageNotFoundComponent } from './modules/page-not-found/page-not-found.component';
import { LayoutComponent } from './modules/layout/layout.component';
import { LoginComponent } from './modules/login/login.component';
import { SignUpComponent } from './modules/signup/signup.component';
import { TreatmentListComponent } from './modules/treatment-list/treatment-list.component';

export const routes: Routes = [
  { path: 'welcome', component: WelcomeComponent },
  { path: 'layout', component: LayoutComponent },
  { path: 'login', component: LoginComponent },
  { path: 'signUp', component: SignUpComponent },
  { path: 'treatments-list', component: TreatmentListComponent },
  { path: '', component: PageNotFoundComponent }, //wildcard, when none route is matched, this one triggers (404 err)
];
