import { Component, Input, OnInit } from '@angular/core';
import { FormGroup } from '@angular/forms';
import { RoleDTO } from '../../../models/RoleDTO';
import {MatTableDataSource, MatTableModule} from '@angular/material/table';
import { MatCheckboxModule } from '@angular/material/checkbox';
import { MatChipsModule } from '@angular/material/chips';
import { RoleService } from '../../../../services/role.service';
import { SnackbarServiceService } from '../../../../services/snackbar-service.service';

@Component({
  selector: 'app-select-role',
  imports: [MatTableModule, MatCheckboxModule, MatChipsModule],
  templateUrl: './select-role.component.html',
  styleUrl: './select-role.component.scss'
})
export class SelectRoleComponent implements OnInit{
  @Input() roleForm!: FormGroup;
  displayedColumns = ['ROL', 'ACCIONES']

  roles: RoleDTO[] = [];
  
  dataSource: RoleDTO[] = [];

  constructor(private roleService: RoleService, private snackBarService: SnackbarServiceService){  }

  async ngOnInit(): Promise<void> {
    this.getAllRoles();
  }

  toggleSelectRole(checked: boolean, rol: RoleDTO){
    let updatedRoles: RoleDTO[];

    if(checked){ /* Add role if selected */
      updatedRoles = [...this.roleForm.get('roles')?.value, rol];
    }else{  /* filter role if deselected */
      updatedRoles = this.roleForm.get('roles')?.value.filter((role: RoleDTO)=> role.id !== rol.id);
    }
    this.roleForm.patchValue({roles: updatedRoles});
  }

  async getAllRoles(): Promise<void>{
    await this.roleService.getAllRoles()
      .then((roles)=>{
        this.dataSource = roles;
      })
      .catch(e => this.snackBarService.showErrorSnackBar("Error al recuperar los roles"));
  }

}
