import { Component } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { ReactiveFormsModule } from '@angular/forms';
import { LayoutComponent } from "./modules/layout/layout.component";
import { MatIconRegistry } from '@angular/material/icon';
import { DomSanitizer } from '@angular/platform-browser';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [CommonModule, MatCardModule, RouterOutlet],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent {
  // Switch to SVG Icon configuration (fixes delayed loading of icons throughout the entire app)
  constructor(
    private matIconRegistry: MatIconRegistry,
    private domSanitizer: DomSanitizer
  ) {
    // Registering Material SVG Icons
    this.matIconRegistry.addSvgIconSet(
      this.domSanitizer.bypassSecurityTrustResourceUrl('https://fonts.gstatic.com/s/materialicons/v132/flUhRq6tzZclQEJ-Vdg-IuiaDsNc.woff2')
    );
  }
}
