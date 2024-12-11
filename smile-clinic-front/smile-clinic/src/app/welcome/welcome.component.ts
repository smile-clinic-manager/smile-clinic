import { Component } from '@angular/core';
import {MatCardModule} from '@angular/material/card';
import {MatButtonModule} from '@angular/material/button';
import {MatToolbarModule} from '@angular/material/toolbar';
import {MatTooltipModule} from '@angular/material/tooltip';

@Component({
  selector: 'app-welcome',
  imports: [MatCardModule, MatButtonModule, MatToolbarModule, MatButtonModule, MatTooltipModule],
  templateUrl: './welcome.component.html',
  styleUrl: './welcome.component.scss'
})
export class WelcomeComponent {

  greet(){
    alert("Welcome to Smile-Clinic!");
  }

}
