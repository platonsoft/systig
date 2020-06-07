import { Injectable } from '@angular/core';
import { Observable, throwError } from 'rxjs';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { catchError, retry } from 'rxjs/operators';
import { Router, ActivatedRoute } from '@angular/router';
import { Respuesta, Pais, Cliente } from 'app/shared/objetos';


const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ClientesService {

  listadoUrl = '/api/crm/clientes';
  getProductoUrl = '/api/crm/cliente/';
  insertarUrl = '/api/crm/cliente';
  actualizarUrl = '/api/crm/cliente/';
  borrarUrl = '/api/crm/cliente/';
  paisesUrl = 'https://restcountries.eu/rest/v2/all';

  constructor(private http: HttpClient, public router: Router, route: ActivatedRoute) { }

  getListaPaises(): Observable<Pais[]> {
    return this.http.get<Pais[]>(this.paisesUrl);
  }

  getListaClientes(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoUrl, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  insertarCliente(cliente: Cliente): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.post<Respuesta>(this.insertarUrl, cliente, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  actualizarCliente(cliente: Cliente, idCliente: number): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.put<Respuesta>(this.actualizarUrl + idCliente, cliente, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  eliminarCliente(idCliente: number): Observable<{}> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.delete(this.borrarUrl + idCliente, httpOptions).pipe(
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
