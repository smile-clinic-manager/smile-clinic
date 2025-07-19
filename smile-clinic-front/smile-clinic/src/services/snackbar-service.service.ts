import { inject, Injectable } from '@angular/core';
import { MatSnackBar } from '@angular/material/snack-bar';

@Injectable({
  providedIn: 'root'
})
export class SnackbarServiceService {
  private readonly _snackBar = inject(MatSnackBar);

  constructor() { }

  showSuccessSnackBar(message: string, action = 'Cerrar'): void {
    this._snackBar.open(message, action, {
      duration: 4500000,
      verticalPosition: 'bottom',
      horizontalPosition: 'end',
      panelClass: 'snack-bar-success'
    })
  }

  showErrorSnackBar(message: string, action = 'Cerrar'): void {
    this._snackBar.open(message, action, {
      duration: 4500000,
      verticalPosition: 'bottom',
      horizontalPosition: 'end',
      panelClass: 'snack-bar-error'
    })
  }

}
