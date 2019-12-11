import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Respuesta, Documento, Productos } from '../objetos/Objetos';
import { retry, catchError, tap } from 'rxjs/operators';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ContabilidadService {
  listadoUrl = '/api/cont/all';
  getDocumentoUrl = '/api/cont/documento/';
  insertarUrl = '/api/cont/documento';
  insertarItemsUrl = '/api/inv/producto/items/';
  actualizarUrl = '/api/cont/documento/';
  borrarUrl = '/api/cont/documento/';
  paisesUrl = 'https://restcountries.eu/rest/v2/all';

  constructor(private http: HttpClient) { }

  getListaDocumentos(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoUrl, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  insertarDocumento(documento: Documento): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.post<Respuesta>(this.insertarUrl, documento, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  crearPedido(documento: Documento, productos: Productos[]): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.post<Respuesta>(this.insertarItemsUrl + documento.idDocumento, productos, httpOptions).pipe(
      tap(
        data => console.log('Completo -> ' + JSON.stringify(data))
      ),
      catchError(this.handleError)
    );
  }

  actualizarDocumento(documento: Documento, idDocumento: number): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.put<Respuesta>(this.actualizarUrl + idDocumento, documento, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  eliminarDocumento(idDocumento: number): Observable<{}> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.delete(this.borrarUrl + idDocumento, httpOptions).pipe(
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
