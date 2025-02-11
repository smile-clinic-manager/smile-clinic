import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatSidenavModule} from '@angular/material/sidenav';
import {MatListModule} from '@angular/material/list';

@Component({
  selector: 'app-side-var',
  imports: [MatSidenavModule, MatButtonModule, MatListModule],
  templateUrl: './side-var.component.html',
  styleUrl: './side-var.component.scss'
})
export class SideVarComponent {

}
