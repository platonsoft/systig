import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { EmpleadosSystig, ItemSplash } from 'src/app/shared/objetos';

@Injectable({
  providedIn: 'root'
})
export class SomosService {

  itemSplash: ItemSplash[] = [
    {
      id: 1,
      titulo: `Creamos Sitios Webs<br />y Aplicaciones`,
      subTitulo: 'Diseño inteligente',
      imagen: 'url(assets/images/home1.png)',
      linkMas: '#'
    },
    {
      id: 2,
      titulo: 'Desarrollo <br />a tu medida',
      subTitulo: 'Dejalo en nuestras manos,<br />nosotros lo haremos por tí',
      imagen: 'url(assets/images/home2.png)',
      linkMas: '#'
    },
    {
      id: 3,
      titulo: '¿Por que trabajar <br />con nostros?',
      subTitulo: 'Somos jóvenes llenos de energía y<br /> queremos crecer contigo',
      imagen: 'url(assets/images/home3.png)',
      linkMas: '#'
    }
  ];
  itemExperiencia: ItemSplash[] = [
    {
      id: 1,
      titulo: `Experiencia comprobada`,
      subTitulo: '',
      descripcion: 'Contamos con un equipo joven y experimentado en constante' +
                   ' crecimiento dispuesto a llegar donde las necesidades de nuestros clientes nos exijan.',
      imagen: 'imagen_expe1.png',
      icono: 'icon_expe1.png',
      linkMas: '#'
    },
    {
      id: 2,
      titulo: `Diseño inteligente`,
      subTitulo: '',
      descripcion: 'Nuestros productos estan diseñados bajo un estudio previo de la experiencia de usuario, ' +
                   'pasando por un proceso de prototipado, sin dejar de lado la identidad de su empresa. ' +
                   'Contamos con un equipo joven y experimentado en constante',
      imagen: 'imagen_expe1.png',
      icono: 'icon_expe2.png',
      linkMas: '#'
    },
    {
      id: 3,
      titulo: `Soporte excepcional`,
      subTitulo: '',
      descripcion: 'Le ofrecemos un soporte mensual para mantener actualizado su web o aplicación, ' +
                   'monitoreamos y detectamos posibles fallos que se corregiran periodicamente.',
      imagen: 'imagen_expe1.png',
      icono: 'icon_expe3.png',
      linkMas: '#'
    }
  ];

  empleadosSystig: EmpleadosSystig[] = [{
    id: 1,
    nombres: 'Jesus Jose',
    apellidos: 'Alcala Pinto',
    descripcion: 'Desarrollador Senior+ de amplia experiencia y adaptacion a las nuevas tecnologias, ' +
                 'Electricista de Profesion y con amplios conocimientos de electronica, mantenimiento ' +
                 'de equipos electronicos y de potencia, Cadista y Modelador BIM entre otras destrezas y habilidades',
    cargo: 'Gerente-Programador',
    habilidades: ['Desarrollo de aplicaciones de escritorio',
                  'Desarrollo de Servicios-uServicios Web',
                  'Desarrollo de sitios Web',
                  'Desarrollo de Aplicaciones Moviles Android'
                ],
    foto: 'avatar_def.svg'
  },
  {
    id: 2,
    nombres: 'Edgar ',
    apellidos: 'Rivera Angulo',
    descripcion: 'Por resumir',
    cargo: 'Gerente-Ventas',
    habilidades: ['Por definir'],
    foto: 'avatar_def.svg'
  },
  {
    id: 3,
    nombres: 'Joel',
    apellidos: 'Leal',
    descripcion: 'Técnico superior en Diseño Gráfico con 10 años de experiencia, ' +
                 'especializado en identidad de marcas, diagramación y diseño de interfaz de usuario.',
    cargo: 'Gerente-Diseño',
    habilidades: ['Branding',
                  'Diseño UX/IU'
                ],
    foto: 'avatar_def.svg'
  }];

  constructor() { }

  getEmpleadosSystig(): Observable<any> {
    return of(this.empleadosSystig);
  }

  getItemsSplash(contenidoItem: string): Observable<any> {
    switch (contenidoItem) {
      case 'experiencia':
        return of(this.itemExperiencia);
      case 'splash':
        return of(this.itemSplash);
      default:
        break;
    }
  }
}
