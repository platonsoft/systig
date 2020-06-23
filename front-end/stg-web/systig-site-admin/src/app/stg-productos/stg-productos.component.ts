import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';
import { ProductosDlgEditComponent } from './productos-dlg-edit/productos-dlg-edit.component';
import { ProductosDlgImpExpComponent } from './productos-dlg-imp-exp/productos-dlg-imp-exp.component';
import { ProductosService } from './productos.service';
import { Productos, ProductosDataSource, Respuesta, Almacen, Categoria, Proveedor } from 'app/shared/objetos';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { ProveedoresService } from 'app/stg-proveedores/proveedores.service';

@Component({
  selector: 'app-productos',
  templateUrl: './stg-productos.component.html',
  styleUrls: ['./stg-productos.component.scss']
})
export class StgProductosComponent implements OnInit {

  displayedColumns: string[] = [ 'descripcion', 'cantidad', 'edicion'];
  PRODUCTOS_DATA: Productos[];
  dataSource = new ProductosDataSource(this.productosService);
  inedit = false;
  myProductosGroup: FormGroup;
  listaAlmacenes: Almacen[];
  listaCategorias: Categoria[];
  listaProveedores: Proveedor[];
  selCategoria: Categoria = {nombre: ''};
  selAlmacen: Almacen = {nombre: ''};
  selProveedor: Proveedor = {idProveedor: 0};
  selProducto: Productos = {};


  constructor(public dialog: MatDialog,
    public productosService: ProductosService,
    public servicioProveedores: ProveedoresService) {

    this.myProductosGroup = new FormGroup({
      nombre: new FormControl('', [
        Validators.required,
      ]),
      cantidadMinima: new FormControl('', [
        Validators.required
      ]),
      descripcion: new FormControl(),
      cantidadOptima: new FormControl('', [
        Validators.required
      ]),
      descuento: new FormControl('', [
        Validators.required
      ]),
      impuesto: new FormControl('', [
        Validators.required
      ]),
      categoria: new FormControl('', [
        Validators.required
      ]),
      almacen: new FormControl('', [
        Validators.required
      ]),
      idProveedor: new FormControl('', [
        Validators.required
      ]),
      unidad: new FormControl('', [
        Validators.required
      ]),
      montoCompra: new FormControl('', [
        Validators.required
      ]),
      montoUnicoDetal: new FormControl('', [
        Validators.required
      ]),
      montoUnicoMayor: new FormControl('', [
        Validators.required
      ]),
      montoCuotasDetal: new FormControl('', [
        Validators.required
      ]),
      montoCuotasMayor: new FormControl('', [
        Validators.required
      ]),
      disponible: new FormControl(),
      isExcento: new FormControl(),
    });

    this.productosService.getListaAlmacenes().subscribe((result: Respuesta) => {
      if (result) {
          this.listaAlmacenes = result.resultado;
          console.log('Lista de Almacenes: ' + JSON.stringify(this.listaAlmacenes));
    }});

    this.productosService.getListaCategorias().subscribe((result: Respuesta) => {
      if (result) {
          this.listaCategorias = result.resultado;
          console.log('Lista de Categorias: ' + JSON.stringify(this.listaCategorias));
    }});

    this.servicioProveedores.getListaProveedores().subscribe((result: Respuesta) => {
      if (result) {
          this.listaProveedores = result.resultado;
    }});

    if (this.selProducto) {
      this.selCategoria = this.selProducto.categoria ? this.selProducto.categoria : {idCategoria: 0, nombre: 'No disponible'};
      this.selAlmacen = this.selProducto.almacen ? this.selProducto.almacen : {idAlmacen: 0, nombre: 'No disponible'};
      this.selProveedor = {idProveedor: this.selProducto.idProveedor};
    }
  }

  ngOnInit() {

  }

  openProductoNuevo(){
    this.inedit = !this.inedit;
  }

  openDialogNuevoProducto(): void {
    const productoNuevo: Productos = {
      idProducto: 0,
      categoria: { nombre: ''},
      almacen: { nombre: '' },
      idProveedor: 0
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

cargaDetallesAlmacen(almacen: Almacen){
  this.selAlmacen = almacen;
  this.productosService.getListaAlmacenes().subscribe((result: Respuesta) => {
    if (result) {
        this.listaAlmacenes = result.resultado;
        console.log('Lista de Almacenes: ' + JSON.stringify(this.listaAlmacenes));
  }});
}

cargaDetallesCategoria(categoria: Categoria){
  this.selCategoria = categoria;
  this.productosService.getListaCategorias().subscribe((result: Respuesta) => {
    if (result) {
        this.listaCategorias = result.resultado;
        console.log('Lista de Categorias: ' + JSON.stringify(this.listaCategorias));
  }});
}
}
