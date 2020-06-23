import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs';
import { ItemSplash } from '../objetos';

@Injectable({
  providedIn: 'root'
})
export class HeaderService {
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

  constructor() { }

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
