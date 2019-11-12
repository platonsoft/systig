import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Respuesta, Proveedor, Pais } from '../objetos/Objetos';

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
    return this.http.get<Respuesta>(this.listadoUrl, httpOptions);
  }

  insertarProveedor(proveedor: Proveedor): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.post<Respuesta>(this.insertarUrl, proveedor, httpOptions);
  }

  actualizarProveedor(proveedor: Proveedor, idProveedor: number): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.put<Respuesta>(this.actualizarUrl + idProveedor, proveedor, httpOptions);
  }

  eliminarProveedor(idProveedor: number): Observable<{}> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.delete(this.borrarUrl + idProveedor, httpOptions);
  }
}
