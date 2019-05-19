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
      imagen: 'url(assets/home1.png)',
      linkMas: '#'
    },
    {
      id: 2,
      titulo: 'Desarrollo <br />a tu medida',
      subTitulo: 'Dejalo en nuestras manos,<br />nosotros lo haremos por tí',
      imagen: 'url(assets/home2.png)',
      linkMas: '#'
    },
    {
      id: 3,
      titulo: '¿Por que trabajar <br />con nostros?',
      subTitulo: 'Somos jóvenes llenos de energía y<br /> queremos crecer contigo',
      imagen: 'url(assets/home3.png)',
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
  listaProductos: ProductoGeneral[] = [{
    id: 1,
    titulo: 'Módulo Facturación',
    subTitulo: 'Módulo Facturación',
    descripcionCorta: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    descripcionLarga: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    imagen: 'assets/home3.png'
  },
  {
    id: 2,
    titulo: 'Módulo Inventario',
    subTitulo: 'Módulo Inventario',
    descripcionCorta: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    descripcionLarga: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    imagen: 'assets/home3.png'
  },
  {
    id: 3,
    titulo: 'Módulo Manejo de Nómina',
    subTitulo: 'Módulo Manejo de Nómina',
    descripcionCorta: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    descripcionLarga: 'Nuestras aplicaciones son desarrolladas bajo los estandares de experiancia de usuario, para lograr un mejor flujo intuitivo, con diseño de interfaz adaptadas a las diferentes plataformas.',
    imagen: 'assets/home3.png'
  }];

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

  getTrabajosRealizados(): Observable<any> {
    return of(this.trabajosRealizados);
  }

  getProductosGenerales(): Observable<any> {
    return of(this.listaProductos);
  }

  getPaisesDisponibles(): Observable<any> {
    return of(this.paisesDisponibles);
  }
}
