import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ProductoDestacado, PaisDisponible } from 'src/app/shared/objetos';

@Injectable({
  providedIn: 'root'
})
export class HomeService {

  paisesDisponibles: PaisDisponible[] = [{
    id: 1,
    code: 'ARG',
    nombre: 'Argentina'
  },
  {
    id: 2,
    code: 'COL',
    nombre: 'Colombia'
  },
  {
    id: 3,
    code: 'ECU',
    nombre: 'Ecuador'
  },
  {
    id: 4,
    code: 'PER',
    nombre: 'Peru'
  },
  {
    id: 5,
    code: 'VEN',
    nombre: 'Venezuela'
  }];

  productoDestacado: ProductoDestacado = {
    id: 1,
    titulo: 'Producto en<br />desarrollo',
    subTitulo: 'Tu negocio virtual',
    descripcionCorta: 'Herramienta de planificacion y gestion de recursos empresariales...',
    descripcionLarga: 'Por definir',
    imagen: 'hmapp1.png'
  };

  constructor() { }

  getPaisesDisponibles(): Observable<any> {
    return of(this.paisesDisponibles);
  }
  getProductoDestacado(): Observable<any> {
    return of(this.productoDestacado);
  }
}
