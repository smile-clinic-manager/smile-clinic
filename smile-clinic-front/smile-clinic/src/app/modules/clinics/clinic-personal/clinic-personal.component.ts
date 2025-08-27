import { AfterViewInit, Component, inject, Input, OnInit, ViewChild } from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { RegisteredUserDTO } from '../../../models/RegisteredUserDTO'; // Adjust the import path as necessary
import { MatIcon } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatButtonModule } from '@angular/material/button';
import { MatChipsModule } from '@angular/material/chips';
import { UserService } from '../../../../services/user.service';
import { SnackbarServiceService } from '../../../../services/snackbar-service.service';
import { MatTooltipModule } from '@angular/material/tooltip';
import { MatDialog, MatDialogRef } from '@angular/material/dialog';
import { AddUserClinicStepperComponent } from '../../../dialogs/add-user-clinic-stepper/add-user-clinic-stepper.component';
import { DeleteUserClinicComponent } from '../../../dialogs/delete-user-clinic/delete-user-clinic.component';
import { EditUserClinicRolesComponent } from '../../../dialogs/edit-user-clinic-roles/edit-user-clinic-roles.component';
import { UpdateUserClinicComponent } from '../../../dialogs/update-user-clinic/update-user-clinic.component';
import { LocalStorageService } from '../../../../services/local-storage.service';
import { userData } from '../../../models/userData';
import { RoleDTO } from '../../../models/RoleDTO';
import { MatProgressSpinnerModule } from '@angular/material/progress-spinner';

@Component({
  selector: 'app-clinic-personal',
  imports: [MatFormFieldModule, MatInputModule, MatTableModule, MatIcon, MatPaginatorModule, MatSortModule, MatButtonModule,
    MatChipsModule, MatTooltipModule, MatProgressSpinnerModule],
  templateUrl: './clinic-personal.component.html',
  styleUrl: './clinic-personal.component.scss'
})
export class ClinicPersonalComponent implements OnInit, AfterViewInit{

  constructor(private userService: UserService, private snackBarService: SnackbarServiceService, private localStorageService: LocalStorageService) {
  }

  @Input() clinicId: string | undefined = '';
  displayedColumns: string[] = [];
  dataSource: MatTableDataSource<RegisteredUserDTO> = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;
  userRole: RoleDTO | undefined = undefined;

  readonly dialog = inject(MatDialog);

  ngOnInit(): void {
    this.getSelectedRole();
    this.getDisplayedColumns();
    this.getUsersByClinicId();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
  }

  getSelectedRole(): void{
    this.userRole = this.localStorageService.getSelectedGlobalRole() ?? undefined;
  }

  getDisplayedColumns(): void{
    this.displayedColumns = this.userRole?.name === 'CLINIC_ADMIN' ? ['USUARIO', 'NOMBRE', 'ROLES', 'ACCIONES'] : ['USUARIO', 'NOMBRE', 'ROLES'];
  }

  getUsersByClinicId(): void {
    this.userService.getClinicUserList(this.clinicId!).then(users=>{
      this.dataSource.data = users;
    }).catch((error)=> this.snackBarService.showErrorSnackBar(error.message))
  }

  getCompleteName(element: RegisteredUserDTO): string {
    return `${element.firstName} ${element.lastName1} ${element.lastName2}`
  }

  applyFilter(event: Event): void {
    const filterValue = (event.target as HTMLInputElement).value;
    this.dataSource.filter = filterValue.trim().toLowerCase(); // aplicamos el filtro

    if (this.dataSource.paginator) {
      this.dataSource.paginator.firstPage();
    }
  }

  openDialogAddUser(): void {
    const dialogRef = this.dialog.open(AddUserClinicStepperComponent, {
      data: { 
        clinicId: this.clinicId,
        clinicUsers: this.dataSource.data
      },
      panelClass: 'lateral-dialog'
    });
    
    this.updateDataSource(dialogRef);
  }



  openEditUserRoleDialog(user: RegisteredUserDTO): void {
    const dialogRef = this.dialog.open(EditUserClinicRolesComponent, {
      data: { 
        clinicId: this.clinicId,
        clinicUser: user
      },
      panelClass: 'lateral-dialog'
    });

    this.updateDataSource(dialogRef);
  }

  openDeleteUserClinicDialog(user: RegisteredUserDTO): void {
    const dialogRef = this.dialog.open(DeleteUserClinicComponent, {
      data: { 
        clinicId: this.clinicId,
        user: user
      },
       panelClass: 'pop-up-dialog'
    });

    this.updateDataSource(dialogRef);
  }

  openUpdateUserClinicDialog(user: RegisteredUserDTO): void {
    const dialogRef = this.dialog.open(UpdateUserClinicComponent, {
      data: { 
        clinicId: this.clinicId,
        user: user,
      },
       panelClass: 'pop-up-dialog'
    });

    this.updateDataSource(dialogRef);
  }

  private updateDataSource(dialogRef: MatDialogRef<any, any>) {
    dialogRef.afterClosed().subscribe((reload) => {
      if(reload){
        this.getUsersByClinicId(); // reload user data to show the newly added user
        this.dataSource.paginator = this.paginator;
        this.dataSource.sort = this.sort;
      }
    });
  }

}
