import { Component, OnInit } from '@angular/core';
import { DesconectadosService } from '../shared/desconectados';

@Component({
  selector: 'stg-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.css']
})
export class ProductosComponent implements OnInit {
  productoDestacado: ProductoDestacado;
  trabajos: TrabajosRealizados[];
  productos: ProductoGeneral[];

  constructor(private servicios: DesconectadosService) { }

  ngOnInit() {
    this.ProductoDestacado();
    this.TrabajoRealizado();
    this.ListaProductosGenerales();
  }

  ProductoDestacado() {
    this.servicios.getProductoDestacado().subscribe(result => {
      this.productoDestacado = result;
    });
  }

  TrabajoRealizado() {
    this.servicios.getTrabajosRealizados().subscribe(result => {
      this.trabajos = result;
    });
  }

  ListaProductosGenerales() {
    this.servicios.getProductosGenerales().subscribe(result => {
      this.productos = result;
    });
  }
}