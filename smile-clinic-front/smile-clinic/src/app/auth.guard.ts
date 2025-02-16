import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { LocalStorageService } from '../services/local-storage.service';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  const localStorageService = inject(LocalStorageService);
  const isLoggedIn: boolean = localStorageService.existsToken();

  if (!isLoggedIn) {
    router.navigate(['/login']); // Redirect to login if not authenticated
    return false;
  }

  return true;
};
