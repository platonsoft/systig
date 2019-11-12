import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Respuesta, Cliente, Pais } from '../objetos/Objetos';
import { HttpClient, HttpHeaders, HttpErrorResponse } from '@angular/common/http';


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

  constructor(private http: HttpClient) { }

  getListaPaises(): Observable<Pais[]> {
    return this.http.get<Pais[]>(this.paisesUrl);
  }

  getListaClientes(): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.get<Respuesta>(this.listadoUrl, httpOptions);
  }

  insertarCliente(cliente: Cliente): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.post<Respuesta>(this.insertarUrl, cliente, httpOptions);
  }

  actualizarCliente(cliente: Cliente, idCliente: number): Observable<Respuesta> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.put<Respuesta>(this.actualizarUrl + idCliente, cliente, httpOptions);
  }

  eliminarCliente(idCliente: number): Observable<{}> {
    const token = localStorage.getItem('tokenSystig');
    httpOptions.headers = httpOptions.headers.set('tokensystig', token);
    return this.http.delete(this.borrarUrl + idCliente, httpOptions);
  }
}
