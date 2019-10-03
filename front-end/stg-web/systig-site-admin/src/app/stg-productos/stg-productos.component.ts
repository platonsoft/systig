import { Component, OnInit } from '@angular/core';
import { MatTableDataSource, MatDialog } from '@angular/material';
import { PRODUCTOS_DATA, ProductoItem, FormacionItem, FORMACION_DATA, ExperienciAItem } from '../objetos/Objetos';
import { ProductosDlgEditComponent } from './productos-dlg-edit/productos-dlg-edit.component';

@Component({
  selector: 'stg-stg-productos',
  templateUrl: './stg-productos.component.html',
  styleUrls: ['./stg-productos.component.scss']
})
export class StgProductosComponent implements OnInit {

  displayedColumns: string[] = [ 'codigo', 'descripcion', 'cantidad', 'edicion'];
  dataSource = new MatTableDataSource<ProductoItem>(PRODUCTOS_DATA);
  constructor(public dialog: MatDialog) { }

  ngOnInit() {
  }

  openDialogEditProducto(itemSelect: FormacionItem, tipoSentencia: string): void {
    const dialogRef = this.dialog.open(ProductosDlgEditComponent, {
      width: '80wh',
      data: {item: itemSelect != null ? itemSelect : new FormacionItem(), sentencia: tipoSentencia}
    });
    dialogRef.afterClosed().subscribe(result => {
      if (result) {
        if (result.sentencia === 'nuevo') {
          FORMACION_DATA.push(result.item);
          this.dataSource = new MatTableDataSource<ProductoItem>(PRODUCTOS_DATA);
        } else if (result.sentencia === 'borrar') {
          FORMACION_DATA.forEach(element => {
            if (element.id === itemSelect.id) {
              FORMACION_DATA.splice(element.id, 1);
            }
          });
        }
      }
      console.log('The dialog was closed');
      console.log('Resukt: ' + JSON.stringify(result));
    });
}

openDialogEditExperiencia(itemSelect: ExperienciAItem, tipoSentencia: string): void {
  const dialogRef = this.dialog.open(ProductosDlgEditComponent, {
    width: '80wh',
    data: {item: itemSelect != null ? itemSelect : new ExperienciAItem(), sentencia: tipoSentencia}
  });
  dialogRef.afterClosed().subscribe(result => {
    console.log('The dialog was closed');
    console.log('Resukt: ' + JSON.stringify(result));
  });
}
}
