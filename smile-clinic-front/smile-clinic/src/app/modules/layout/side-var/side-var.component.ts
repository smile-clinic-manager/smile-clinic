import { AfterViewInit, Component, Input, Signal, signal, ViewChild } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import {MatListModule} from '@angular/material/list';
import { Router, RouterOutlet } from '@angular/router';
import { MatIconModule } from '@angular/material/icon';
import {MatExpansionModule} from '@angular/material/expansion';
import {MatSidenav, MatSidenavModule} from '@angular/material/sidenav';
import { MatTooltip, MatTooltipModule } from '@angular/material/tooltip';
import {FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from '@angular/forms';
import { LocalStorageService } from '../../../../services/local-storage.service';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatSelectModule } from '@angular/material/select';
import { userData } from '../../../models/userData';
import { ClinicRole } from '../../../models/ClinicRole';

@Component({
  selector: 'app-side-var',
  imports: [MatSidenavModule, MatButtonModule, MatListModule, RouterOutlet, MatIconModule, MatExpansionModule,
    MatTooltipModule, ReactiveFormsModule, FormsModule, MatFormFieldModule, MatSelectModule
  ],
  templateUrl: './side-var.component.html',
  styleUrl: './side-var.component.scss'
})
export class SideVarComponent implements AfterViewInit{
  @ViewChild(MatSidenav) sidenav!: MatSidenav;
  @Input({required: true}) isMenuOpen!: Signal<boolean>;
  private isFirstRender: boolean = true;
  user: userData | null = null;

  clinicRoleList: {id: string, name: string}[] = [];

  constructor(private readonly router: Router, private localStorageService: LocalStorageService){ }

  ngOnInit(): void {
    this.user = this.localStorageService.getUserData();
    this.setSelectedClinic(this.user.roles[0]);
  }
  
  globalClinicRoleform = new FormGroup({
    globalClinic: new FormControl(),
    globalRole: new FormControl(),
  })

  ngAfterViewInit(){
    //Evitamos que en el 1er renderizado detecte el cambio de estado del menú para evitar loop infinito
    this.sidenav.openedChange.subscribe(()=>{ if(this.isFirstRender) this.isFirstRender = false; })
  }

  setSelectedClinic(clinicRole: ClinicRole): void {
    this.globalClinicRoleform.get('globalClinic')!.setValue(clinicRole); // Set clinic
    this.clinicRoleList = clinicRole.roles; // Set role options
    this.globalClinicRoleform.get('globalRole')!.setValue(this.clinicRoleList[0]); //Set default role

    this.localStorageService.setSelectedGlobalClinic(clinicRole);
    this.localStorageService.setSelectedGlobalRole(this.clinicRoleList[0]);

  }
  
  redirectToClinics(){
    this.router.navigate(['clinic-list']);
  }

  redirectToPatients(){
    this.router.navigate(['patients-list']);
  }

  redirectToHome(){
    this.router.navigate(['home']);
  }
}
