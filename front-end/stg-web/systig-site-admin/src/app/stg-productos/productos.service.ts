import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';
import { Observable, throwError } from 'rxjs';
import { Productos, Respuesta } from '../objetos/Objetos';

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
    return this.http.get<Productos>(this.listadoUrl + codigo, httpOptions);
  }

  getListaProductos(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoUrl, httpOptions);
  }

  getListaAlmacenes(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoAlmacenesUrl, httpOptions);
  }

  getListaCategorias(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoCategoriasUrl, httpOptions);
  }

  insertarProducto(producto: Productos): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.post<Respuesta>(this.insertarUrl, producto, httpOptions);
  }

  actualizarProducto(producto: Productos, idProducto: number): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.put<Respuesta>(this.actualizarUrl + idProducto, producto, httpOptions);
  }

  eliminarProducto(idProducto: number): Observable<{}> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.delete(this.borrarUrl + idProducto, httpOptions);
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
  }
}
