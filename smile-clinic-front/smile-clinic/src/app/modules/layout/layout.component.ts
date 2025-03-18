import { Component, signal, ViewChild } from '@angular/core';
import {provideNativeDateAdapter} from '@angular/material/core';
import { SideVarComponent } from './side-var/side-var.component';
import { HeaderComponent } from './header/header.component';
import { FooterComponent } from './footer/footer.component';

@Component({
  selector: 'app-layout',
  imports: [SideVarComponent, HeaderComponent, FooterComponent],
  providers: [provideNativeDateAdapter()],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss',
})
export class LayoutComponent {
  @ViewChild(SideVarComponent) sideVarComponent!: SideVarComponent;
  public isMenuOpen = signal<boolean>(true); //if its not initialized set to true

  toggleSideVar(){
    this.isMenuOpen.set(!this.isMenuOpen());
  }


  

}
