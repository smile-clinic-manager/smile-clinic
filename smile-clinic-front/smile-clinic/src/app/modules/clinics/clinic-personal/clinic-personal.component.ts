import { AfterViewInit, Component, Input, OnInit, ViewChild } from '@angular/core';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import {MatInputModule} from '@angular/material/input';
import {MatFormFieldModule} from '@angular/material/form-field';
import { RegisteredUserDTO } from '../../../models/RegisteredUserDTO'; // Adjust the import path as necessary
import { MatIcon } from '@angular/material/icon';
import { MatPaginator, MatPaginatorModule } from '@angular/material/paginator';
import { MatSort, MatSortModule } from '@angular/material/sort';
import { MatButtonModule } from '@angular/material/button';
import {MatChipsModule} from '@angular/material/chips';




@Component({
  selector: 'app-clinic-personal',
  imports: [MatFormFieldModule, MatInputModule, MatTableModule, MatIcon, MatPaginatorModule, MatSortModule, MatButtonModule,
    MatChipsModule],
  templateUrl: './clinic-personal.component.html',
  styleUrl: './clinic-personal.component.scss'
})
export class ClinicPersonalComponent implements OnInit, AfterViewInit{
  
  USERS_PRUEBA: RegisteredUserDTO[] = [
    {
      id: 1,
      username: "jdoe",
      email: "jdoe@example.com",
      firstName: "John",
      lastName1: "Doe",
      lastName2: "Smith",
      dni: "12345678A",
      roles: [ {'id': '1', 'name':'ADMIN', 'permissions': null }, {'id': '2', 'name':'USER', 'permissions': null } ],
      jwtToken: "eyJhbGciOiJIUzI1NiIsInR...",
      refreshToken: "dGhpc2lzaW9ubHlhcmVmcmV..."
    },
    {
      id: 2,
      username: "mgarcia",
      email: "mgarcia@example.com",
      firstName: "Maria",
      lastName1: "Garcia",
      lastName2: "Lopez",
      dni: "87654321B",
      roles: [ {'id': '2', 'name':'USER', 'permissions': null } ],
      jwtToken: "eyJhbGciOiJIUzI1NiIsInQ...",
      refreshToken: "dGhpc2lzaW9ubHlhcmVmcm..."
    },
    {
      id: 3,
      username: "arodriguez",
      email: "arodriguez@example.com",
      firstName: "Antonio",
      lastName1: "Rodriguez",
      lastName2: "Fernandez",
      dni: "11223344C",
      roles: null,
      jwtToken: "eyJhbGciOiJIUzI1NiIsInR5c...",
      refreshToken: "dGhpc2lzaW9ubHlhcmVm..."
    }
  ];

  @Input() clinicId: string | undefined = '';
  displayedColumns: string[] = ['USUARIO', 'NOMBRE', 'ROLES', 'ACCIONES'];
  dataSource: MatTableDataSource<RegisteredUserDTO> = new MatTableDataSource(this.USERS_PRUEBA);

  @ViewChild(MatPaginator) paginator!: MatPaginator;
  @ViewChild(MatSort) sort!: MatSort;

  
  
  ngOnInit(): void {

  }

  ngAfterViewInit() {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    console.log(this.dataSource.sort);
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

}
