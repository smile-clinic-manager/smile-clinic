import { Component, EventEmitter, Input, OnInit, Output, signal, Signal } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatToolbarModule } from '@angular/material/toolbar';
import { Router } from '@angular/router';
import { AuthService } from '../../../../services/auth.service';
import { MatIcon } from '@angular/material/icon';
import {MatIconModule} from '@angular/material/icon';
import {MatMenuModule} from '@angular/material/menu';
import { MatDivider } from '@angular/material/divider';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { RegisteredUserDTO } from '../../../models/RegisteredUserDTO';
import { LocalStorageService } from '../../../../services/local-storage.service';
import { userData } from '../../../models/userData';

@Component({
  selector: 'app-header',
  imports: [MatToolbarModule, MatButtonModule, MatIcon, MatIconModule, MatMenuModule, MatFormFieldModule,
    MatSelectModule, MatFormFieldModule, ReactiveFormsModule, FormsModule
  ],
  templateUrl: './header.component.html',
  styleUrl: './header.component.scss'
})
export class HeaderComponent implements OnInit{
  @Output() toggleSideVarEvent: EventEmitter<void> = new EventEmitter(); 
  @Input({required: true}) isMenuOpen!: Signal<boolean>;
  user: userData | undefined = undefined;

  constructor(private readonly authService: AuthService, private readonly router: Router, 
    private localStorageService: LocalStorageService){  }

  ngOnInit(): void {
    this.user = this.localStorageService.getUserData();
  }


  logout(): void {
    this.authService.logout();
    this.router.navigate(['/login']);
  }

  toggleSideVar(){
    this.toggleSideVarEvent.emit();
  }



}
