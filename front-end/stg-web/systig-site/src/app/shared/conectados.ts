import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { map } from 'rxjs/operators';
import { environment } from 'src/environments/environment.prod';
import { Observable } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({providedIn: 'root'})
export class ConectadosService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'my-auth-token'
    })
  };

  constructor(private httpClient: HttpClient, private router: Router) { }

  login(username: string, password: string): Observable<any> {
    console.log('Usduario --> ' + username + 'clave --> ' + password);

    const usuarioActual = window.btoa(username + ':' + password);
    console.log('Usduario Actual --> ' + usuarioActual);

    const headersX = new HttpHeaders({
      Authorization : 'Basic ' + btoa(username + ':' + password),
      'X-Requested-With': 'XMLHttpRequest'
  });


    this.httpOptions.headers = this.httpOptions.headers.set('Authorization', `Basic ${usuarioActual}`);
    this.httpOptions.headers = this.httpOptions.headers.set('X-Requested-With', 'XMLHttpRequest');

    return this.httpClient.get<any>(`/api/login`, {headers: headersX})
        .pipe(map((token: RespuestaWS) => {
            if (token) {
                localStorage.setItem('tokenSystig', JSON.stringify(token));
                window.location.assign('http://localhost:4201/inicio/' + token.token);
            }
            return token;
        }));
}

logout() {
    // remove user from local storage to log user out
    localStorage.removeItem('currentUser');
}
}
