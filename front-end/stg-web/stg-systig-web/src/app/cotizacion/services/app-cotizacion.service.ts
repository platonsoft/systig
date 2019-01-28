import { Contrato } from './../../shared/model/contrato';
import { Observable } from 'rxjs';
import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { tap, catchError } from 'rxjs/operators';

const COTIZA_API_RUTA = "cotiza?";
const PREGUNTAS_API_RUTA = "cotiza/preguntas?";
const COTIZA_API_KEY = "DEMO";

@Injectable({
  providedIn: 'root'
})
export class AppCotizacionService {

  constructor(private httClient:HttpClient) { }

  getServiciosById(qid:number):Observable<any>{
    return this.httClient.get<any>(COTIZA_API_RUTA + 'api_key=' + COTIZA_API_KEY + '&dpd=' + qid);
  }

  getPreguntasList():Observable<any>{
    return this.httClient.get<any>(PREGUNTAS_API_RUTA + 'api_key=' + COTIZA_API_KEY);
  }

  /** POST: Agregar un nuevo contrato al servidor */
  addContrato (contrato: Contrato): Observable<Contrato> {
    return this.httClient.post<Contrato>(COTIZA_API_RUTA,['DEMO',contrato],{
      headers:new HttpHeaders({
        'Content-Type':'application/json'
      })
    }).pipe(tap((contrat: Contrato) => console.log(contrat)));
  }
}
