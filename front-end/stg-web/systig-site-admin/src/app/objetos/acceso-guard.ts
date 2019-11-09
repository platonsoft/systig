import { Injectable } from '@angular/core';
import { Router, CanActivate, ActivatedRouteSnapshot, RouterStateSnapshot, ActivatedRoute, ParamMap } from '@angular/router';

@Injectable({ providedIn: 'root' })
export class AccesoGuard implements CanActivate {

    constructor(private router: Router) { }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {
      const tokenRec: string = localStorage.getItem('tokenSystig');

      if (tokenRec) {
        // Verificar la validez del token en el ws
        return true;
      }
      window.location.assign('http://localhost:4200');
      return false;
    }
}
