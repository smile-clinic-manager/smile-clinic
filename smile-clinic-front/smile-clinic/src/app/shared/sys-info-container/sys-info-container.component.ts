import { Component, Input } from '@angular/core';
import { MatIconModule } from '@angular/material/icon';

@Component({
  selector: 'app-sys-info-container',
  imports: [MatIconModule],
  templateUrl: './sys-info-container.component.html',
  styleUrl: './sys-info-container.component.scss'
})
export class SysInfoContainerComponent {
  @Input({required: true}) message!: string;
}
