import {CanActivateFn, Router} from '@angular/router';
import {inject} from "@angular/core";
import {AuthenticationService} from "./authentication.service";

export const authGuard: CanActivateFn = ( ) => {
  const authenticationService = inject(AuthenticationService);
  const router = inject(Router);
  if (authenticationService.getUser()) {
    return true;
  } else {
    router.navigate(['/sign-in']);
    return false;
  }
};
