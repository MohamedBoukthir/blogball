import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import {AuthenticationService} from "./authentication.service";


export const loggedInAuthGuard: CanActivateFn = () => {
  const authenticationService = inject(AuthenticationService);
  const router = inject(Router);
  if (authenticationService.getUser()) {
    router.navigate(['/']);
    return false;
  } else {
    return true;
  }
};
