import { Component, OnInit, Input } from '@angular/core';
import { DesconectadosService } from '../shared/desconectados';

@Component({
  selector: 'stg-global-header',
  templateUrl: './global-header.component.html',
  styleUrls: ['./global-header.component.css']
})
export class GlobalHeaderComponent implements OnInit {

  itemSplash: ItemSplash[];
  itemSplasActivo: ItemSplash;
  idItemActivo = 0;
  interval: any;
  @Input() paginaActiva = 'home';
  @Input() conNavegador = true;

  constructor(private servicios: DesconectadosService) { }

  ngOnInit() {
    switch (this.paginaActiva) {
      case 'home':
        this.ItemsSplash();
        this.itemSplasActivo = this.itemSplash[this.idItemActivo];
        this.conNavegador = true;
        this.IntervalChangeSplash();
        break;
      case 'productos':
        this.ItemsSplash();
        this.itemSplasActivo = this.itemSplash[1];
        this.conNavegador = false;
        clearInterval(this.interval);
        break;
      case 'servicios':
        this.ItemsSplash();
        this.itemSplasActivo = this.itemSplash[0];
        this.conNavegador = false;
        clearInterval(this.interval);
        break;
      case 'somos':
        this.ItemsSplash();
        this.itemSplasActivo = this.itemSplash[2];
        this.conNavegador = false;
        clearInterval(this.interval);
        break;
      case 'contacto':
        this.ItemsSplash();
        this.itemSplasActivo = {
          id: 0,
          titulo: '',
          subTitulo: '',
          imagen: 'none',
          linkMas: ''
        };
        this.conNavegador = false;
        clearInterval(this.interval);
        break;
      default:
        break;
    }
  }

  IntervalChangeSplash() {
    this.interval = setInterval(() => {
      this.idItemActivo += 1;
      if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
        this.itemSplasActivo = this.itemSplash[this.idItemActivo];
      } else {
        this.idItemActivo = 0;
        this.itemSplasActivo = this.itemSplash[this.idItemActivo];
      }
    }, 5000);
  }

  NextNavegador() {
    if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
      clearInterval(this.interval);
      this.idItemActivo = this.idItemActivo + 1;
      this.itemSplasActivo = this.itemSplash[this.idItemActivo];
    }
  }

  PrevNavegador() {
    if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
      clearInterval(this.interval);
      this.idItemActivo = this.idItemActivo - 1;
      this.itemSplasActivo = this.itemSplash[this.idItemActivo];
    } else if (this.idItemActivo === 3) {
      clearInterval(this.interval);
      this.idItemActivo = 0;
      this.itemSplasActivo = this.itemSplash[this.idItemActivo];
    }
  }

  ItemsSplash() {
    this.servicios.getItemsSplash('splash').subscribe(result => {
      this.itemSplash = result;
    });
  }
}
