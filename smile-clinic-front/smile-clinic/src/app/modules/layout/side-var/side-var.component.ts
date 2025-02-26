import { Component, signal, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import { Router, RouterOutlet } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatSidenav, MatSidenavModule} from '@angular/material/sidenav';

@Component({
  selector: 'app-side-var',
  imports: [MatSidenavModule, MatButtonModule, MatListModule, RouterOutlet, MatIconModule, MatExpansionModule],
  templateUrl: './side-var.component.html',
  styleUrl: './side-var.component.scss'
})
export class SideVarComponent {
  @ViewChild(MatSidenav) sidenav!: MatSidenav;
  readonly panelOpenState = signal(false);

  constructor( private readonly router: Router ){ }

  toggleSideNav() {
    this.sidenav.toggle();
  }
  
  redirectToClinics(){
    this.router.navigate(['clinic-list']);
  }
}
