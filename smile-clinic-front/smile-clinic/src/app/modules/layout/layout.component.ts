import { Component } from '@angular/core';

import { MatButton } from '@angular/material/button';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { MatCard } from '@angular/material/card';
import { MatDatepickerModule } from '@angular/material/datepicker';
import { MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import {provideNativeDateAdapter} from '@angular/material/core';
import { FormControl, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { LoginService} from '../../../services/login-service.service';
import { LocalStorageService } from '../../../services/local-storage.service';
import { SideVarComponent } from './side-var/side-var.component';
import { HeaderComponent } from './header/header.component';
import { RouterOutlet } from '@angular/router';
import { FooterComponent } from './footer/footer.component';

@Component({
  selector: 'app-layout',
  imports: [SideVarComponent, HeaderComponent, FooterComponent],
  providers: [provideNativeDateAdapter()],
  templateUrl: './layout.component.html',
  styleUrl: './layout.component.scss',
})
export class LayoutComponent {

/*   checkInterceptor(){
    const params: Map<string, any> = new Map();
    params.set('token', localStorage.getItem('token')?? '');
    this.api.get(this.apiEndpointHelper.createUrlWithQueryParameters('/auth/validate-token', params)).subscribe({
      next: (response)=>{
        alert("Validated token: " + response);
      },
      error: (error)=>{
        alert("Error validating the token" + error.message);
      }
    })
  } */


  

}
