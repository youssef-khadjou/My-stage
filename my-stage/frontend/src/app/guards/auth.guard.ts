import { inject, PLATFORM_ID } from '@angular/core';
import { isPlatformBrowser } from '@angular/common';
import { CanActivateFn, Router } from '@angular/router';

export const authGuard: CanActivateFn = () => {
  const router = inject(Router);
  const platformId = inject(PLATFORM_ID);

  if (isPlatformBrowser(platformId)) {
    const utilisateur = localStorage.getItem('utilisateur');
    if (utilisateur) {
      return true;
    }
    router.navigate(['/']);
    return false;
  }

  return true;
};
