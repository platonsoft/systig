import { Component, OnInit } from '@angular/core';
import { ProductoDestacado, TrabajosRealizados, ProductoGeneral } from 'src/app/shared/objetos';
import { ProductosService } from './productos.service';

@Component({
  selector: 'stg-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.scss']
})
export class ProductosComponent implements OnInit {

  productoDestacado: ProductoDestacado;
  trabajos: TrabajosRealizados[];
  productos: ProductoGeneral[];
  onExpandProduct: boolean;

  constructor(private prdServ: ProductosService) { }

  ngOnInit(): void {
    this.ProductoDestacado();
    this.TrabajoRealizado();
    this.ListaProductosGenerales();
    this.onExpandProduct = false;
  }

  ProductoDestacado() {
    this.prdServ.getProductoDestacado().subscribe(result => {
      this.productoDestacado = result;
    });
  }

  TrabajoRealizado() {
    this.prdServ.getTrabajosRealizados().subscribe(result => {
      this.trabajos = result;
    });
  }

  ListaProductosGenerales() {
    this.prdServ.getProductosGenerales().subscribe(result => {
      this.productos = result;
    });
  }
  ToggleProductosDetail(){
    this.onExpandProduct = !this.onExpandProduct;
  }

}
