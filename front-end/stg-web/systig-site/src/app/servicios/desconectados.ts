import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, Observer, of } from 'rxjs';

@Injectable({providedIn: 'root'})
export class DesconectadosService {
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
            descripcionCorta: '',
            descripcionLarga: '',
            seleccionado: false
          },
          {
            id: 2,
            imagen: 'mapp.svg',
            titulo: 'Desarrollo movil',
            descripcionCorta: '',
            descripcionLarga: '',
            seleccionado: false
          },
          {
            id: 3,
            imagen: 'shop.svg',
            titulo: 'Tienda online',
            descripcionCorta: '',
            descripcionLarga: '',
            seleccionado: false
          },
          {
            id: 4,
            imagen: 'brand.svg',
            titulo: 'Branding',
            descripcionCorta: '',
            descripcionLarga: '',
            seleccionado: false
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
        seleccionado: false
      },
      {
        id: 2,
        imagen: 'mapp.svg',
        titulo: 'Desarrollo movil',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false
      },
      {
        id: 3,
        imagen: 'shop.svg',
        titulo: 'Tienda online',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false
      },
      {
        id: 4,
        imagen: 'brand.svg',
        titulo: 'Branding',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false
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
        seleccionado: false
      },
      {
        id: 2,
        imagen: 'mapp.svg',
        titulo: 'Control 2',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false
      },
      {
        id: 3,
        imagen: 'shop.svg',
        titulo: 'Control 3',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false
      },
      {
        id: 4,
        imagen: 'brand.svg',
        titulo: 'Control 4',
        descripcionCorta: '',
        descripcionLarga: '',
        seleccionado: false
      }
    ],
    seleccionUnica: false,
    indice: 1,
    esFinal: true
  };

  productoDestacado: ProductoDestacado = {
    id: 1,
    titulo: 'Producto <br />en desarrollo',
    subTitulo: 'Tu negocio virtual',
    descripcionCorta: 'Herramienta de planificacion y gestion de recursos empresariales...',
    descripcionLarga: 'Por definir',
    imagen: 'hmapp1.png'
  };

  itemSplash: ItemSplash[] = [
    {
      id: 1,
      titulo: `Creamos <br />Aplicaciones <br />y Sitios Webs`,
      subTitulo: 'Diseño inteligente',
      imagen: 'assets/home1.png',
      linkMas: '#'
    },
    {
      id: 2,
      titulo: 'Desarrollo <br />a tu medida',
      subTitulo: 'Dejalo en nuestras manos,<br />nosotros lo haremos por tí',
      imagen: 'assets/home2.png',
      linkMas: '#'
    },
    {
      id: 3,
      titulo: '¿Por que trabajar <br />con nostros?',
      subTitulo: 'Somos jóvenes llenos de energía y<br /> queremos crecer contigo',
      imagen: 'assets/home3.png',
      linkMas: '#'
    }
  ];
  empleadosSystig: EmpleadosSystig[] = [{
    id: 1,
    nombres: 'Jesus Jose',
    apellidos: 'Alcala Pinto',
    cargo: 'Gerente-Programador',
    foto: 'avatar_def.svg'
  },
  {
    id: 2,
    nombres: 'Edgar ',
    apellidos: 'Rivera Angulo',
    cargo: 'Gerente-Ventas',
    foto: 'avatar_def.svg'
  },
  {
    id: 3,
    nombres: 'Joel',
    apellidos: 'Leal',
    cargo: 'Gerente-Diseño',
    foto: 'avatar_def.svg'
  }];
  constructor(private httpClient: HttpClient) { }

  getCotizacion(): Observable<any> {
    return of(this.cotizacionCompleta);
  }

  SiguienteEtapaCotizacion(etapaPrevia: EtapaCotizacion): Observable<any> {
    return of(this.etapaMock2);
  }

  getProductoDestacado(): Observable<any> {
    return of(this.productoDestacado);
  }

  getItemsSplash(): Observable<any> {
    return of(this.itemSplash);
  }

  getEmpleadosSystig(): Observable<any> {
    return of(this.empleadosSystig);
  }
}
