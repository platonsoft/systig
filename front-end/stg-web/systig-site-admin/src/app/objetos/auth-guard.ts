import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute, ParamMap } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AuthGuard implements CanActivate {

  token: string;

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      const tokenRec: string = route.paramMap.get('token');
      if (tokenRec) {
        localStorage.setItem('tokenSystig', tokenRec);
        this.router.navigate(['/principal']);
        return true;
      }
      window.location.assign('http://localhost:4200');
      return false;
    }
}
