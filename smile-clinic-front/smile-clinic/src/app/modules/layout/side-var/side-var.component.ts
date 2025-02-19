import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';
import { Router, RouterOutlet } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-side-var',
  imports: [MatSidenavModule, MatButtonModule, MatListModule, RouterOutlet, MatIconModule],
  templateUrl: './side-var.component.html',
  styleUrl: './side-var.component.scss'
})
export class SideVarComponent {
  isOpen = true;

  constructor( private readonly router: Router ){ }

  toggleSideNav() {
    this.isOpen = !this.isOpen;
  }
  
  redirectToClinics(){
    this.router.navigate(['clinic-list']);
  }
}
