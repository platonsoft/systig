import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { retry, catchError } from 'rxjs/operators';
import { Pais, Respuesta, Proveedor } from 'app/shared/objetos';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ProveedoresService {

  listadoUrl = '/api/prv/proveedores';
  getProductoUrl = '/api/prv/proveedor/';
  insertarUrl = '/api/prv/proveedor';
  actualizarUrl = '/api/prv/proveedor/';
  borrarUrl = '/api/prv/proveedor/';
  paisesUrl = 'https://restcountries.eu/rest/v2/all';

  constructor(private http: HttpClient) { }

  getListaPaises(): Observable<Pais[]> {
    return this.http.get<Pais[]>(this.paisesUrl);
  }

  getListaProveedores(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoUrl, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  insertarProveedor(proveedor: Proveedor): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.post<Respuesta>(this.insertarUrl, proveedor, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  actualizarProveedor(proveedor: Proveedor, idProveedor: number): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.put<Respuesta>(this.actualizarUrl + idProveedor, proveedor, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  eliminarProveedor(idProveedor: number): Observable<{}> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.delete(this.borrarUrl + idProveedor, httpOptions).pipe(
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
