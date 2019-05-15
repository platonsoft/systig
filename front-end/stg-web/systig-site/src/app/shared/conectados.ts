import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';

@Injectable({providedIn: 'root'})
export class ConectadosService {
  constructor(private httpClient: HttpClient) { }

}
