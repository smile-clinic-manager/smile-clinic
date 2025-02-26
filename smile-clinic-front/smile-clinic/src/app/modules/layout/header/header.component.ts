import { Component, EventEmitter, Input, Output, signal, Signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router } from '@angular/router';
import { AuthService } from '../../../../services/auth.service';
import { MatIcon } from '@angular/material/icon';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import { MatDivider } from '@angular/material/divider';

@Component({
  selector: 'app-header',
  imports: [MatToolbarModule, MatButtonModule, MatIcon, MatIconModule, MatMenuModule, MatDivider],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent {
  @Output() toggleSideVarEvent: EventEmitter<void> = new EventEmitter(); 
  @Input({required: true}) isMenuOpen!: Signal<boolean>;
  
  constructor(private readonly authService: AuthService, private readonly router: Router){  }  

  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  toggleSideVar(){
    this.toggleSideVarEvent.emit();
  }



}
