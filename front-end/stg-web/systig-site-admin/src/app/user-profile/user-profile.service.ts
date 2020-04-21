import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Respuesta, Propietario } from '../objetos/Objetos';
import { retry, catchError } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class UserProfileService {

  listadoUsuariosUrl = '/api/sesion/usuarios';
  paramConfiguracionUrl = '/api/sesion/configuracion';
  guardarPropietarioUrl = '/api/sesion/propietario';

  constructor(private http: HttpClient) { }

  getListaUsuarios(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoUsuariosUrl, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  getConfiguracion(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.paramConfiguracionUrl, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  guardarPropietario(propietario: Propietario): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    console.log('Propietario: ' + JSON.stringify(propietario));

    return this.http.post<Respuesta>(this.guardarPropietarioUrl, propietario, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      // Ocurrio un error del lado del cliente.
      console.error('Ocurrio un error:', error.error.message);
    } else {
      // The backend returned an unsuccessful response code.
      // The response body may contain clues as to what went wrong,
      if (error.status === 401) {
        console.error('Error de credenciales, reiniciando el servidor');
        sessionStorage.clear();
        window.location.assign('http://localhost:4200');
      }
    }
    // return an observable with a user-facing error message
    return throwError(
      'Ocurrio un error desconocido, intente mas tarde');
  }
}
