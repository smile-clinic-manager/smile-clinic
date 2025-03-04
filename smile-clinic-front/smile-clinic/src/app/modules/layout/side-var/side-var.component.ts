import { AfterViewInit, Component, Input, Signal, signal, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import { Router, RouterOutlet } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatSidenav, MatSidenavModule} from '@angular/material/sidenav';
import { MatTooltip, MatTooltipModule } from '@angular/material/tooltip';

@Component({
  selector: 'app-side-var',
  imports: [MatSidenavModule, MatButtonModule, MatListModule, RouterOutlet, MatIconModule, MatExpansionModule,
    MatTooltipModule
  ],
  templateUrl: './side-var.component.html',
  styleUrl: './side-var.component.scss'
})
export class SideVarComponent implements AfterViewInit{
  @ViewChild(MatSidenav) sidenav!: MatSidenav;
  @Input({required: true}) isMenuOpen!: Signal<boolean>;
  private isFirstRender: boolean = true;

  constructor( private readonly router: Router ){ }


  ngAfterViewInit(){
    //Evitamos que en el 1er renderizado detecte el cambio de estado del menú para evitar loop infinito
    this.sidenav.openedChange.subscribe(()=>{ if(this.isFirstRender) this.isFirstRender = false; })
  }
  
  redirectToClinics(){
    this.router.navigate(['clinic-list']);
  }

  redirectToHome(){
    this.router.navigate(['home']);
  }
}
