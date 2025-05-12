import { Component, OnInit } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { MatIconModule } from '@angular/material/icon';
import { MatMenuModule } from '@angular/material/menu';
import { userData } from '../../../models/userData';
import { UserService } from '../../../../services/user.service';
import { LocalStorageService } from '../../../../services/local-storage.service';
import { MatDividerModule } from '@angular/material/divider';
import { RegisteredUserDTO } from '../../../models/RegisteredUserDTO';

@Component({
  selector: 'app-profile',
  imports: [MatCardModule, MatIconModule, MatMenuModule, MatButtonModule, MatDividerModule],
  templateUrl: './profile-detail.component.html',
  styleUrl: './profile-detail.component.scss'
})
export class ProfileDetailComponent implements OnInit {

  data: userData | undefined = undefined;
  user: RegisteredUserDTO | undefined = undefined;

  constructor(private localStorageService: LocalStorageService,
              private userService: UserService) {
  }

  ngOnInit(): void {
    this.data = this.localStorageService.getUserData();
    this.userService.getUserById(this.data?.id.toString()).then((user: RegisteredUserDTO) => {
      this.user = user;
    }).catch((error) => {
      console.error('Error fetching user data:', error);
    });
  }

  //updateProfileForm here

  updateUser(): void {
    if (this.user) { //Check data and set the new data into user
      this.userService.updateUser(this.user).then(() => {
        console.log('User updated successfully');
      }).catch((error) => {
        console.error('Error updating user:', error);
      });
    }
  }

  deleteUser(): void {
    this.userService.deleteUser(this.data?.id?.toString() ?? '').then(() => {
      console.log('User deleted successfully');
    }).catch((error) => {
      console.error('Error deleting user:', error);
    });
  }

}
