import { Component, OnInit } from '@angular/core';
import { DesconectadosService } from '../servicios/desconectados';

@Component({
  selector: 'stg-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  itemSplash: ItemSplash[];
  itemSplasActivo: ItemSplash;
  idItemActivo = 0;
  interval: any;
  menuLat = false;
  productoDestacado: ProductoDestacado;

  constructor(private servicios: DesconectadosService) { }

  ngOnInit() {
    this.ItemsSplash();
    this.itemSplasActivo = this.itemSplash[this.idItemActivo];

    this.IntervalChangeSplash();
    this.ProductoDestacado();
  }

  IntervalChangeSplash() {
    this.interval = setInterval(() => {
      if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
        this.itemSplasActivo = this.itemSplash[this.idItemActivo];
      } else {
        this.idItemActivo = 0;
        this.itemSplasActivo = this.itemSplash[this.idItemActivo];
      }
      this.idItemActivo += 1;
    }, 5000);
  }

  NextNavegador() {
    if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
      clearInterval(this.interval);
      this.itemSplasActivo = this.itemSplash[this.idItemActivo + 1];
      this.idItemActivo = this.idItemActivo + 1;
    }
  }

  PrevNavegador() {
    if (this.idItemActivo >= 0 && this.idItemActivo <= 2) {
      clearInterval(this.interval);
      this.itemSplasActivo = this.itemSplash[this.idItemActivo - 1];
      this.idItemActivo = this.idItemActivo - 1;
    } else if (this.idItemActivo === 3) {
      clearInterval(this.interval);
      this.itemSplasActivo = this.itemSplash[this.idItemActivo - 2];
      this.idItemActivo = this.idItemActivo - 2;
    }
  }

  ToggleMenuLat() {
    this.menuLat = !this.menuLat;
  }

  ProductoDestacado() {
    this.servicios.getProductoDestacado().subscribe(result => {
      this.productoDestacado = result;
    });
  }

  ItemsSplash() {
    this.servicios.getItemsSplash().subscribe(result => {
      this.itemSplash = result;
    });
  }
}
