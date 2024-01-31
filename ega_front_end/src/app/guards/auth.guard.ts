// auth.guard.ts
import { Injectable, Inject } from '@angular/core';
import { CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, Router } from '@angular/router';
import { AuthService } from '../services/auth.service'; // Adjust the path accordingly

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(@Inject(AuthService) private authService: AuthService, private router: Router) {}

  canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): boolean {
    if (this.authService.isAuthenticatedUser()) {
      return true;
    } else {
      // Redirect to the login page if not authenticated
      this.router.navigate(['/login']);
      return false;
    }
  }
}
