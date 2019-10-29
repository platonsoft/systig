import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError, of } from 'rxjs';
import { catchError, retry } from 'rxjs/operators';
import { Productos, ProductoItem, PRODUCTOS_DATA } from '../objetos/Objetos';

const httpOptions = {
  headers: new HttpHeaders({
    'Content-Type':  'application/json',
    Authorization: 'my-auth-token'
  })
};

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  listadoUrl = 'http://localhost:8090/api/inv/productos';
  insertarUrl = 'assets/config.json';
  actualizarUrl = 'assets/config.json';

  constructor(private http: HttpClient) { }

  getProducto(codigo: string): Observable<Productos> {
    const token = sessionStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('Authorization', token);
    return this.http.get<Productos>(this.listadoUrl + '?codigo=' + codigo, httpOptions);
  }

  getListaProductos(): Observable<ProductoItem[]> {
    const token = sessionStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('Authorization', token);
    return this.http.get<ProductoItem[]>(this.listadoUrl, httpOptions);
  }

  getListaProductos2(): Observable<ProductoItem[]> {
    return of(PRODUCTOS_DATA);
  }

  insertarProducto(producto: Productos): Observable<Productos> {
    const token = sessionStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('Authorization', token);
    return this.http.post<Productos>(this.insertarUrl, producto, httpOptions)
    .pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  actualizarProducto(producto: Productos): Observable<Productos> {
    const token = sessionStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('Authorization', token);
    return this.http.put<Productos>(this.actualizarUrl, producto, httpOptions)
    .pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  eliminarProducto(codigo: string): Observable<{}> {
    const token = sessionStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('Authorization', token);
    return this.http.delete(this.actualizarUrl, httpOptions)
    .pipe(
      retry(3),
      catchError(this.handleError)
    );
  }

  private handleError(error: HttpErrorResponse) {
    if (error.error instanceof ErrorEvent) {
      console.error('Ocurrio un error:', error.error.message);
    } else {
      console.error(
        `Error Codigo ${error.status}, ` +
        `Error: ${error.error}`);
    }
    return throwError(
      'Algo anda mal; Por favor intentalo mas tarde.');
  };
}
