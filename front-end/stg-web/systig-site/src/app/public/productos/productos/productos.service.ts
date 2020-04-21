import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ProductoGeneral, TrabajosRealizados, ProductoDestacado } from 'src/app/shared/objetos';

@Injectable({
  providedIn: 'root'
})
export class ProductosService {

  listaProductos: ProductoGeneral[] = [{
    id: 1,
    titulo: 'Módulo Facturación',
    subTitulo: 'Módulo Facturación',
    descripcionCorta: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    descripcionLarga: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    imagen: 'assets/images/home3.png'
  },
  {
    id: 2,
    titulo: 'Módulo Inventario',
    subTitulo: 'Módulo Inventario',
    descripcionCorta: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    descripcionLarga: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    imagen: 'assets/images/home3.png'
  },
  {
    id: 3,
    titulo: 'Módulo Manejo de Nómina',
    subTitulo: 'Módulo Manejo de Nómina',
    descripcionCorta: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    descripcionLarga: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    imagen: 'assets/images/home3.png'
  }];

  trabajosRealizados: TrabajosRealizados[] = [{
    id: 1,
    cliente: 'DIRECCION REGIONAL DE SALUD DEL ESTADO MONAGAS',
    descripcion: 'Aplicación de Gestión de Nomina denominada CATANA ',
    fecha: '01/01/2015'
  }, {
    id: 2,
    cliente: 'DIRECCION REGIONAL DE SALUD DEL ESTADO MONAGAS',
    descripcion: 'Aplicación para la Administración y control de documentos y facturas. ',
    fecha: '01/01/2015'
  }, {
    id: 3,
    cliente: 'DIRECCION REGIONAL DE SALUD DEL ESTADO MONAGAS',
    descripcion: 'Aplicación para monitoreo de dispositivos de medición como PLC y sensores. ',
    fecha: '01/01/2015'
  },{
    id: 4,
    cliente: 'DIRECCION REGIONAL DE SALUD DEL ESTADO MONAGAS',
    descripcion: 'Aplicación para el control de entrada/salida de activos materiales. ',
    fecha: '01/01/2015'
  },{
    id: 5,
    cliente: 'DIRECCION REGIONAL DE SALUD DEL ESTADO MONAGAS',
    descripcion: 'Aplicación de gestión Empresarial SUITE PCANDYSOFT para el manejo de nómina, personal, servicios contables y administrativos.(En Curso).',
    fecha: '01/01/2015'
  },{
    id: 6,
    cliente: 'DIRECCION REGIONAL DE SALUD DEL ESTADO MONAGAS',
    descripcion: 'Otras de menor envergadura como Módulos de mantenimiento para bases de datos, normalizaciones de Bases y Almacenes de datos, módulos VB/LISP para AutoCAD y Civil3D, aplicaciones para servicios especializados como gestión de clientes de centros médicos.',
    fecha: '01/01/2015'
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

  getTrabajosRealizados(): Observable<any> {
    return of(this.trabajosRealizados);
  }

  getProductosGenerales(): Observable<any> {
    return of(this.listaProductos);
  }

  getProductoDestacado(): Observable<any> {
    return of(this.productoDestacado);
  }
}
