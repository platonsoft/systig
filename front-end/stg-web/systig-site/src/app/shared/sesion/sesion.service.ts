import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Router } from '@angular/router';
import { Observable } from 'rxjs';
import { RespuestaWS } from '../objetos';
import { map } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class SesionService {

  httpOptions = {
    headers: new HttpHeaders({
      'Content-Type':  'application/json',
      Authorization: 'my-auth-token'
    })
  };

  constructor(private httpClient: HttpClient, private router: Router) {

  }

  login(username: string, password: string): Observable<any> {
      const usuarioActual = window.btoa(username + ':' + password);
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

  registrar(propietario: any): Observable<any> {
    return this.httpClient.post<any>('/api/registro', propietario)
        .pipe(map((respuesta: RespuestaWS) => {
            if (respuesta) {
                alert('Usuario Creado Satisfactoriamente, Recibira un email con las credenciales');
            }
            return respuesta;
        }));
  }

  logout() {
      localStorage.removeItem('currentUser');
  }
}
