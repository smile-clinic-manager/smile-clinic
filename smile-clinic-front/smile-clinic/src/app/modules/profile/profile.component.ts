import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { userData } from '../../models/userData';
import { RoleService } from '../../../services/role.service';
import { UserService } from '../../../services/user.service';
import { LocalStorageService } from '../../../services/local-storage.service';
import { MatDividerModule } from '@angular/material/divider';

@Component({
  selector: 'app-profile',
  imports: [MatCardModule, MatIconModule, MatMenuModule, MatButtonModule, MatDividerModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.scss'
})
export class ProfileComponent implements OnInit {

  user: userData | undefined = undefined;

  constructor(private localStorageService: LocalStorageService) {
    
  }

  ngOnInit(): void {
    this.user = this.localStorageService.getUserData();
  }

  updateUser(): void {
  }

  deleteUser(): void {
  }

}
