import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { EtapaCotizacion, PaisDisponible, CotizacionGeneral } from '../objetos';

@Injectable({
  providedIn: 'root'
})
export class CotizacionService {

  cotizacionCompleta: CotizacionGeneral = {
    id: 1,
    codigo: '00001',
    etapas: [
      {
        id: 1,
        titulo: 'Seleccion de Servicios',
        descripcionCorta: 'Seleccion de servicios',
        descripcionLarga: 'Seleccion de los servicios por los que cotizar',
        items: [
          {
            id: 1,
            imagen: 'wapp.svg',
            titulo: 'Desarrollo web',
            descripcionCorta: 'Desarrollamos páginas web de calidad de acuerdo a las necesidades de tu marca.',
            descripcionLarga: '<ul><li>Asesoría especializada</li><li>Plantilla prediseñada</li><li>Responsive</li><li>Integración con redes sociales</li><li>Gestor de noticias o blog personal</li><li>Chat Interactivo</li><li>Soporte y actualizaciones</li></ul>',
            seleccionado: false,
            disponible: true
          },
          {
            id: 2,
            imagen: 'mapp.svg',
            titulo: 'Desarrollo movil',
            descripcionCorta: '',
            descripcionLarga: '',
            seleccionado: false,
            disponible: false
          },
          {
            id: 3,
            imagen: 'shop.svg',
            titulo: 'Tienda online',
            descripcionCorta: '',
            descripcionLarga: '',
            seleccionado: false,
            disponible: true
          },
          {
            id: 4,
            imagen: 'brand.svg',
            titulo: 'Branding',
            descripcionCorta: '',
            descripcionLarga: '',
            seleccionado: false,
            disponible: true
          }
        ],
        seleccionUnica: true,
        indice: 1,
        esFinal: false
      }
    ],
    etapaActual: 0,
    totalDescuentos: 0,
    subtotal: 0,
    total: 0,
  };

  etapaMock1: EtapaCotizacion = {
    id: 1,
    titulo: 'Seleccion de Servicios',
    descripcionCorta: 'Seleccion de servicios',
    descripcionLarga: 'Seleccion de los servicios por los que cotizar',
    items: [
      {
        id: 1,
        imagen: 'wapp.svg',
        titulo: 'Desarrollo web',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false,
        disponible: true
      },
      {
        id: 2,
        imagen: 'mapp.svg',
        titulo: 'Desarrollo movil',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false,
        disponible: false
      },
      {
        id: 3,
        imagen: 'shop.svg',
        titulo: 'Tienda online',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false,
        disponible: true
      },
      {
        id: 4,
        imagen: 'brand.svg',
        titulo: 'Branding',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false,
        disponible: true
      }
    ],
    seleccionUnica: true,
    indice: 1,
    esFinal: false
  };
  etapaMock2: EtapaCotizacion = {
    id: 2,
    titulo: 'Etapa 2 de pruebas',
    descripcionCorta: 'Etapa 2 de pruebas',
    descripcionLarga: 'Etapa 2 de pruebas',
    items: [
      {
        id: 1,
        imagen: 'wapp.svg',
        titulo: 'Control 1',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false,
        disponible: true
      },
      {
        id: 2,
        imagen: 'mapp.svg',
        titulo: 'Control 2',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false,
        disponible: true
      },
      {
        id: 3,
        imagen: 'shop.svg',
        titulo: 'Control 3',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false,
        disponible: true
      },
      {
        id: 4,
        imagen: 'brand.svg',
        titulo: 'Control 4',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false,
        disponible: true
      }
    ],
    seleccionUnica: false,
    indice: 1,
    esFinal: true
  };

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

  constructor() { }

  getCotizacion(): Observable<any> {
    return of(this.cotizacionCompleta);
  }

  SiguienteEtapaCotizacion(etapaPrevia: EtapaCotizacion): Observable<any> {
    return of(this.etapaMock2);
  }

  getPaisesDisponibles(): Observable<any> {
    return of(this.paisesDisponibles);
  }
}
