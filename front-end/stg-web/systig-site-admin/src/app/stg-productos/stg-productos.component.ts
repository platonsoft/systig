import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { ProductoItem, ClienteItem } from '../objetos/Objetos';
import { ProductosDlgEditComponent } from './productos-dlg-edit/productos-dlg-edit.component';
import { ProductosDlgImpExpComponent } from './productos-dlg-imp-exp/productos-dlg-imp-exp.component';
import { ProductosService } from './productos.service';

@Component({
  selector: 'stg-stg-productos',
  templateUrl: './stg-productos.component.html',
  styleUrls: ['./stg-productos.component.scss']
})
export class StgProductosComponent implements OnInit {

  displayedColumns: string[] = [ 'descripcion', 'cantidad', 'edicion'];
  PRODUCTOS_DATA: ProductoItem[];
  dataSource = new MatTableDataSource<ProductoItem>(this.PRODUCTOS_DATA);
  constructor(public dialog: MatDialog, public productosService: ProductosService) {
    this.productosService.getListaProductos().subscribe(lista =>{
      this.PRODUCTOS_DATA = lista;
      this.dataSource = new MatTableDataSource<ProductoItem>(this.PRODUCTOS_DATA);
    });
  }

  ngOnInit() {

  }

  openDialogEditProducto(itemSelect: ClienteItem, tipoSentencia: string): void {
    const dialogRef = this.dialog.open(ProductosDlgEditComponent, {
      width: '80vw',
      data: {item: itemSelect != null ? itemSelect : new ProductoItem(), sentencia: tipoSentencia}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (result.sentencia === 'nuevo') {
          this.productosService.insertarProducto(result.item).subscribe(
            producto => this.PRODUCTOS_DATA.push(result.item));
          // PRODUCTOS_DATA.push(result.item);
          this.dataSource = new MatTableDataSource<ProductoItem>(this.PRODUCTOS_DATA);
        } else if (result.sentencia === 'borrar') {
          this.productosService.eliminarProducto(result.item.codigo).subscribe(
            resultado => {
              this.PRODUCTOS_DATA.forEach(element => {
                if (element.id === itemSelect.id) {
                  this.PRODUCTOS_DATA.splice(element.id, 1);
                }
              });
            });
        }
      }
      console.log('The dialog was closed');
      console.log('Resukt: ' + JSON.stringify(result));
    });
}

openDialogImportarExportarProducto(): void {
  const dialogRef = this.dialog.open(ProductosDlgImpExpComponent, {
    width: '80vw'
  });
  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
    console.log('Resukt: ' + JSON.stringify(result));
  });
}


}
