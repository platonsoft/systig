import { Component, OnInit } from '@angular/core';
import { DesconectadosService } from '../shared/desconectados';

@Component({
  selector: 'stg-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  productoDestacado: ProductoDestacado;
  constructor(private servicios: DesconectadosService) { }

  ngOnInit() {
    this.ProductoDestacado();
  }

  ProductoDestacado() {
    this.servicios.getProductoDestacado().subscribe(result => {
      this.productoDestacado = result;
    });
  }
}
