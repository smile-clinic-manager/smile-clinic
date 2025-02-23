import { Component } from '@angular/core';
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
