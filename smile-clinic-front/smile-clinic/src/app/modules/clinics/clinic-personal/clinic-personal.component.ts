import { AfterViewInit, Component, inject, Input, OnInit, ViewChild } from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { RegisteredUserDTO } from '../../../models/RegisteredUserDTO'; // Adjust the import path as necessary
import { MatIcon } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatButtonModule } from '@angular/material/button';
import {MatChipsModule} from '@angular/material/chips';
import { UserService } from '../../../../services/user.service';
import { SnackbarServiceService } from '../../../../services/snackbar-service.service';
import { MatTooltipModule } from '@angular/material/tooltip';
import {
  MAT_DIALOG_DATA,
  MatDialog,
  MatDialogActions,
  MatDialogClose,
  MatDialogContent,
  MatDialogRef,
  MatDialogTitle,
} from '@angular/material/dialog';
import { AddUserClinicComponent } from '../../../dialogs/add-user-clinic-stepper/add-user-clinic/add-user-clinic.component';
import { AddUserClinicStepperComponent } from '../../../dialogs/add-user-clinic-stepper/add-user-clinic-stepper.component';



@Component({
  selector: 'app-clinic-personal',
  imports: [MatFormFieldModule, MatInputModule, MatTableModule, MatIcon, MatPaginatorModule, MatSortModule, MatButtonModule,
    MatChipsModule, MatTooltipModule],
  templateUrl: './clinic-personal.component.html',
  styleUrl: './clinic-personal.component.scss'
})
export class ClinicPersonalComponent implements OnInit, AfterViewInit{

  constructor(private userService: UserService, private snackBarService: SnackbarServiceService) {
  }

  @Input() clinicId: string | undefined = '';
  displayedColumns: string[] = ['USUARIO', 'NOMBRE', 'ROLES', 'ACCIONES'];
  dataSource: MatTableDataSource<RegisteredUserDTO> = new MatTableDataSource();
  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  readonly dialog = inject(MatDialog);

  ngOnInit(): void {
    this.getUsersByClinicId();
  }

  ngAfterViewInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
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
    
    dialogRef.afterClosed().subscribe(() => {
      this.getUsersByClinicId(); // reload user data to show the newly added user
      this.dataSource.paginator = this.paginator;
      this.dataSource.sort = this.sort;
    })
  }

}
