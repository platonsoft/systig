import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { EtapaCotizacion, PaisDisponible, CotizacionGeneral } from '../objetos';
import { AngularFirestore, AngularFirestoreCollection } from '@angular/fire/firestore';

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

  etapaMock: EtapaCotizacion[] = [
    {
      id: 2,
      titulo: '¿Qué nivel de calidad quieres para tu proyecto?',
      descripcionCorta: 'Etapa 2 de pruebas',
      descripcionLarga: 'Etapa 2 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'OPTIMA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'CALIDAD-PRECIO',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'NO IMPORTA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: false
    },
    {
      id: 3,
      titulo: '¿Qué tipo de proyecto quieres desarrollar?',
      descripcionCorta: 'Etapa 3 de pruebas',
      descripcionLarga: 'Etapa 3 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'ANDROID',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'iOS',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'WIN PHONE',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 4,
          imagen: 'shop.svg',
          titulo: 'ANDROID+iOS',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 5,
          imagen: 'shop.svg',
          titulo: 'ANDROID+iOS+WPHONE',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 6,
          imagen: 'shop.svg',
          titulo: 'WEB',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 7,
          imagen: 'shop.svg',
          titulo: 'W DESKTOP',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 8,
          imagen: 'shop.svg',
          titulo: 'LINUX DESKTOP',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 9,
          imagen: 'shop.svg',
          titulo: 'MAC DESKTOP',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 10,
          imagen: 'shop.svg',
          titulo: 'BRANDING',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 11,
          imagen: 'shop.svg',
          titulo: 'SERVICIO WEB (WS)',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: false
    },
    {
      id: 4,
      titulo: '¿Cuan avanzado esta tu proyecto?',
      descripcionCorta: 'Etapa 2 de pruebas',
      descripcionLarga: 'Etapa 2 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'SOLO IDEA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'BOCETO LISTO',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'EN DESARROLLO',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: false
    },
    {
      id: 5,
      titulo: '¿Cuál de las frases identificaria mejor tu proyecto?',
      descripcionCorta: 'Etapa 3 de pruebas',
      descripcionLarga: 'Etapa 3 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'IMAGEN CORPORATIVA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'WEB DE NOTICIAS',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'TIENDA VIRTUAL',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 4,
          imagen: 'shop.svg',
          titulo: 'GESTION DE ALMACEN',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 5,
          imagen: 'shop.svg',
          titulo: 'ANDROID+iOS+WPHONE',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 6,
          imagen: 'shop.svg',
          titulo: 'GESTION DE PERSONAL',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 7,
          imagen: 'shop.svg',
          titulo: 'GESTION DE FACTURACION',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 8,
          imagen: 'shop.svg',
          titulo: 'SISTEMA ADMINISTRATIVO',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 9,
          imagen: 'shop.svg',
          titulo: 'GESTION DE CLIENTES',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 10,
          imagen: 'shop.svg',
          titulo: 'SISTEMA DE UBICACIÓN',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 11,
          imagen: 'shop.svg',
          titulo: 'CALCULOS SIMPLES',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 12,
          imagen: 'shop.svg',
          titulo: 'CALCULOS COMPLEJOS',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 13,
          imagen: 'shop.svg',
          titulo: 'GESTION PERSONALIZADA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: false
    },
    {
      id: 6,
      titulo: '¿Como desea que sea su interface?',
      descripcionCorta: 'Etapa 2 de pruebas',
      descripcionLarga: 'Etapa 2 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'SENCILLA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'BASICA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'PROFESIONAL',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 4,
          imagen: 'shop.svg',
          titulo: 'PERSONALIZADA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 5,
          imagen: 'shop.svg',
          titulo: 'REPLICA DE OTRA(S)',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 6,
          imagen: 'shop.svg',
          titulo: 'YA TENGO',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 7,
          imagen: 'shop.svg',
          titulo: 'NO NECESITA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: false
    },
    {
      id: 7,
      titulo: '¿desea ganar dinero con su proyecto?',
      descripcionCorta: 'Etapa 2 de pruebas',
      descripcionLarga: 'Etapa 2 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'APP GRATUITA SIN PUBLICIDAD',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'APP GRATUITA CON PUBLICIDAD',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'APP DE PAGO',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 4,
          imagen: 'shop.svg',
          titulo: 'COMPRAS DENTRO DE APP',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 5,
          imagen: 'shop.svg',
          titulo: 'REPLICA DE OTRA(S)',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 6,
          imagen: 'shop.svg',
          titulo: 'YA TENGO',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 7,
          imagen: 'shop.svg',
          titulo: 'NO NECESITA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: false
    },
    {
      id: 8,
      titulo: '¿Desea tener un sistema de control de sesion?',
      descripcionCorta: 'Etapa 2 de pruebas',
      descripcionLarga: 'Etapa 2 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'CON REDES',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'CON EMAIL',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'CON USUARIO PERSONAL',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 4,
          imagen: 'shop.svg',
          titulo: 'SIN LOGIN',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 5,
          imagen: 'shop.svg',
          titulo: 'NO LO SE',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: false
    },
    {
      id: 9,
      titulo: '¿Su proyecto se integra con otro?',
      descripcionCorta: 'Etapa 2 de pruebas',
      descripcionLarga: 'Etapa 2 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'INTEGRADO A SITIO WEB',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'INTEGRADO A APP MOVIL',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'INTEGRADO A WS',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 4,
          imagen: 'shop.svg',
          titulo: 'SIN INTEGRACION',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 5,
          imagen: 'shop.svg',
          titulo: 'NO LO SE',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: false
    },
    {
      id: 10,
      titulo: '¿Desea que su proyecto tenga un panel de control?',
      descripcionCorta: 'Etapa 2 de pruebas',
      descripcionLarga: 'Etapa 2 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'SIMPLE',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'CON ROLES',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'SOLO EDITOR DE PERFIL',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 4,
          imagen: 'shop.svg',
          titulo: 'SIN PANEL',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 5,
          imagen: 'shop.svg',
          titulo: 'NO LO SE',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: false
    },
    {
      id: 11,
      titulo: '¿Cuantos idiomas tendra su proyecto?',
      descripcionCorta: 'Etapa 2 de pruebas',
      descripcionLarga: 'Etapa 2 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'UNO',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'BILINGUE',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'VARIOS IDIOMAS',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 4,
          imagen: 'shop.svg',
          titulo: 'NO LO SE',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: false
    },
    {
      id: 12,
      titulo: '¿Tienes un servidor?',
      descripcionCorta: 'Etapa 2 de pruebas',
      descripcionLarga: 'Etapa 2 de pruebas',
      items: [
        {
          id: 1,
          imagen: 'wapp.svg',
          titulo: 'SI, USO AWS',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 2,
          imagen: 'mapp.svg',
          titulo: 'SI, USO FIREBASE',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 3,
          imagen: 'shop.svg',
          titulo: 'SI, USO BLUEHOST',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 4,
          imagen: 'shop.svg',
          titulo: 'SI, USO GODADDY',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 5,
          imagen: 'shop.svg',
          titulo: 'SI, USO OTRO',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 6,
          imagen: 'shop.svg',
          titulo: 'NO, PERO QUIERO UNO',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        },
        {
          id: 6,
          imagen: 'shop.svg',
          titulo: 'NO LO NECESITA',
          descripcionCorta: '',
          descripcionLarga: '',
          seleccionado: false,
          disponible: true
        }
      ],
      seleccionUnica: true,
      indice: 1,
      esFinal: true
    }
  ];

  private paisesCollection: AngularFirestoreCollection<PaisDisponible[]>;

  constructor(private afs: AngularFirestore) { }

  getCotizacion(): Observable<any> {
    return of(this.cotizacionCompleta);
  }

  SiguienteEtapaCotizacion(etapaIndex: number): Observable<any> {
    return of(this.etapaMock[etapaIndex]);
  }

  getPaisesDisponibles(): Observable<any> {
    this.paisesCollection = this.afs.collection<PaisDisponible[]>('paises');
    return this.paisesCollection.valueChanges();
  }
}
