import { Routes } from '@angular/router';
import { WelcomeComponent } from './welcome/welcome.component';
import { LayoutComponent } from './layout/layout.component';
import { PageNotFoundComponent } from './page-not-found/page-not-found.component';

export const routes: Routes = [
    {path: 'welcome', component: WelcomeComponent},
    {path: 'home', component: LayoutComponent},
    {path: '', component: PageNotFoundComponent}, //wildcard, when none route is matched, this one triggers (404 err)
];
