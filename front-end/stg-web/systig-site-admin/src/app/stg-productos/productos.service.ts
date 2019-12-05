import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Productos, Respuesta } from '../objetos/Objetos';
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
export class ProductosService {

  listadoUrl = '/api/inv/productos';
  listadoPorProveedorUrl = '/api/inv/productos/proveedor/';
  listadoAlmacenesUrl = '/api/inv/almacenes';
  listadoCategoriasUrl = '/api/inv/categorias';
  listadoProveedoresUrl = '/api/inv/proveedores';
  getProductoUrl = '/api/inv/producto/';
  insertarUrl = '/api/inv/producto';
  actualizarUrl = '/api/inv/producto/';
  borrarUrl = '/api/inv/producto/';

  constructor(private http: HttpClient) { }

  getProducto(codigo: string): Observable<Productos> {
    const token = sessionStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Productos>(this.listadoUrl + codigo, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  getListaProductos(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoUrl, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  getListaProductosProveedor(idProveedor: number): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoPorProveedorUrl + idProveedor, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  getListaAlmacenes(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoAlmacenesUrl, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  getListaCategorias(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoCategoriasUrl, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  insertarProducto(producto: Productos): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.post<Respuesta>(this.insertarUrl, producto, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  actualizarProducto(producto: Productos, idProducto: number): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.put<Respuesta>(this.actualizarUrl + idProducto, producto, httpOptions).pipe(
      retry(2),
      catchError(this.handleError)
    );
  }

  eliminarProducto(idProducto: number): Observable<{}> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.delete(this.borrarUrl + idProducto, httpOptions).pipe(
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
