import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const utilisateur = localStorage.getItem('utilisateur');

  if (utilisateur) {
    return true;
  }

  router.navigate(['/']);
  return false;
};
