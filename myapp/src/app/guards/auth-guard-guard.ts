import { inject } from '@angular/core';
import { CanActivateFn, Router } from '@angular/router';
import { AuthService } from '../../servicess/auth-service';



export const authGuardGuard: CanActivateFn = (route, state) => {
  
  const authService = inject(AuthService);
  const router = inject(Router);

  const token = authService.getToken();
 

 if (authService.isLoggedIn()) {
    return true; // ✅ allow dashboard
  } else {
    router.navigate(['/unauthorized']); // ❌ block
    return false;
  }
};


