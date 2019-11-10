import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ProductosDataSource, Productos, Respuesta } from '../objetos/Objetos';
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
  PRODUCTOS_DATA: Productos[];
  dataSource = new ProductosDataSource(this.productosService);

  constructor(public dialog: MatDialog, public productosService: ProductosService) {
  }

  ngOnInit() {

  }

  openDialogNuevoProducto(): void {
    const productoNuevo: Productos = {
      idProducto: 0,
      almacen: {idAlmacen: 0},
      categoria: {idCategoria: 0},
      proveedor: {idProveedor: 0}
    };

    const dialogRef = this.dialog.open(ProductosDlgEditComponent, {
      width: '80vw',
      data: {item: productoNuevo, sentencia: 'nuevo'}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
          this.productosService.insertarProducto(result.item).subscribe(( resultado: Respuesta) => {
            if (result) {
                this.dataSource = new ProductosDataSource(this.productosService);
                console.log('Despues:  ' + JSON.stringify(resultado));
          }});
        }
      console.log('The dialog was closed');
      console.log('Resukt: ' + JSON.stringify(result));
    });
}

openDialogEditProducto(itemSelect: Productos, idProducto: number): void {
  const dialogRef = this.dialog.open(ProductosDlgEditComponent, {
    width: '80vw',
    data: {item: itemSelect, sentencia: 'editar'}
  });
  dialogRef.afterClosed().subscribe(result => {
    if (result) {
        this.productosService.actualizarProducto(result.item, idProducto)
                              .subscribe(( resultado: Respuesta) => {
          if (result) {
              this.dataSource = new ProductosDataSource(this.productosService);
              console.log('Despues:  ' + JSON.stringify(resultado));
          }
        });
      }
    console.log('The dialog was closed');
    console.log('Resukt: ' + JSON.stringify(result));
    });
}

openDialogBorrarProducto(idProducto: number): void {
  const dialogRef = this.dialog.open(ProductosDlgEditComponent, {
    width: '80vw',
    data: {item: null, sentencia: 'borrar'}
  });
  dialogRef.afterClosed().subscribe(result => {
    if (result) {
      this.productosService.eliminarProducto(idProducto).subscribe(resultado => {
        if (resultado) {
            this.dataSource = new ProductosDataSource(this.productosService);
            console.log('Despues:  ' + JSON.stringify(resultado));
        }
      });
    }
    console.log('The dialog was closed');
    console.log('Resukt: ' + JSON.stringify(result));
  });
}

dlgFuncionDesarrollo() {
  alert('La opcion que ha seleccionado esta en desarrollo, en cualquier momento sera activado');
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
