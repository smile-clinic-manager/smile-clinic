import { AbstractControl, ValidationErrors, ValidatorFn } from '@angular/forms';

export function nonEmptyArrayValidator(): ValidatorFn {
  return (control: AbstractControl): ValidationErrors | null => {
    return Array.isArray(control.value) && control.value.length === 0 ? { emptyArray: true } : null;
  };
}
