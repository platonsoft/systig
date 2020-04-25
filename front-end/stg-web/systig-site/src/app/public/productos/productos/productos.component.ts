import { Component, OnInit } from '@angular/core';
import { ProductoGeneral } from 'src/app/shared/objetos';
import { ProductosService } from './productos.service';

@Component({
  selector: 'stg-productos',
  templateUrl: './productos.component.html',
  styleUrls: ['./productos.component.scss']
})
export class ProductosComponent implements OnInit {

  productoDestacado: ProductoGeneral;
  productos: ProductoGeneral[];
  onExpandProduct: boolean;
  private favs: any = [];

  constructor(private prdServ: ProductosService) { }

  ngOnInit(): void {
    this.ProductoDestacadoSe();
    this.ListaProductosGenerales();
    this.onExpandProduct = false;
  }

  ProductoDestacadoSe() {
    this.prdServ.getProductosGenerales().subscribe(prods => {
      this.prdServ.getVotaciones().subscribe(voac => {
        prods.forEach((prod, ix) => {
          this.favs.push({rank: 0, producto: prod});
          voac.forEach(voto => {
            if (voto.idProducto === this.favs[ix].producto.id) {
              this.favs[ix].rank += voto.voto;
            }
          });
        });
        this.productoDestacado = this.favs.sort((a, b) => {
          return b.rank - a.rank;
        })[0].producto;
      });
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
