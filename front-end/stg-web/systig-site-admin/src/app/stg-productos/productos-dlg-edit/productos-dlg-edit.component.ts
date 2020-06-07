import { Component, OnInit, Inject } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { MyErrorStateMatcher } from 'src/app/objetos/MyErrorStateMatcher';
import { MatDialogRef, MAT_DIALOG_DATA, MatTableDataSource } from '@angular/material';
import { Historial, PRODUCTOS_HISTORIAL_DATA, Almacen, Categoria, Respuesta, Proveedor, Productos } from 'src/app/objetos/stg-objetos';
import { ProductosService } from '../productos.service';
import { map } from 'rxjs/operators';
import { ProveedoresService } from 'src/app/stg-proveedores/proveedores.service';

@Component({
  selector: 'stg-productos-dlg-edit',
  templateUrl: './productos-dlg-edit.component.html',
  styleUrls: ['./productos-dlg-edit.component.scss']
})
export class ProductosDlgEditComponent implements OnInit {

  myGroup: FormGroup;
  matcher = new MyErrorStateMatcher();
  listaAlmacenes: Almacen[];
  listaCategorias: Categoria[];
  listaProveedores: Proveedor[];


  selCategoria: Categoria = {nombre: ''};
  selAlmacen: Almacen = {nombre: ''};
  selProveedor: Proveedor = {idProveedor: 0};

  displayedColumns: string[] = ['descripcion', 'accion', 'fecha'];
  dataSourceHistorico = new MatTableDataSource<Historial>(PRODUCTOS_HISTORIAL_DATA);

  constructor(public dialogRef: MatDialogRef<ProductosDlgEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public servicioProducto: ProductosService,
              public servicioProveedores: ProveedoresService) {
    this.myGroup = new FormGroup({
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
      disponible: new FormControl('', []),
      isExcento: new FormControl('', []),
    });

    this.servicioProducto.getListaAlmacenes().subscribe((result: Respuesta) => {
      if (result) {
          this.listaAlmacenes = result.resultado;
          console.log('Lista de Almacenes: ' + JSON.stringify(this.listaAlmacenes));
    }});

    this.servicioProducto.getListaCategorias().subscribe((result: Respuesta) => {
      if (result) {
          this.listaCategorias = result.resultado;
          console.log('Lista de Categorias: ' + JSON.stringify(this.listaCategorias));
    }});

    this.servicioProveedores.getListaProveedores().subscribe((result: Respuesta) => {
      if (result) {
          this.listaProveedores = result.resultado;
    }});

    if (this.data.item) {
      this.selCategoria = this.data.item.categoria;
      this.selAlmacen = this.data.item.almacen;
      this.selProveedor = {idProveedor: this.data.item.idProveedor};
    }
  }

  ngOnInit() {

  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  // convenience getter for easy access to form fields
  get f() { return this.myGroup.controls; }


  onSubmit() {
    // stop here if form is invalid
    if (this.myGroup.invalid) {
      console.log('Invalidado');
      return;
    }

    const productoItem: Productos = this.myGroup.value;
    this.data.item = productoItem;

    this.data.item.almacen = this.selAlmacen;
    this.data.item.categoria = this.selCategoria;
    this.data.item.proveedor = this.selProveedor;

    console.log('Antes:  ' + JSON.stringify(productoItem));

  }

  cargaDetallesAlmacen(almacen: Almacen){
    this.selAlmacen = almacen;
    this.servicioProducto.getListaAlmacenes().subscribe((result: Respuesta) => {
      if (result) {
          this.listaAlmacenes = result.resultado;
          console.log('Lista de Almacenes: ' + JSON.stringify(this.listaAlmacenes));
    }});
  }

  cargaDetallesCategoria(categoria: Categoria){
    this.selCategoria = categoria;
    this.servicioProducto.getListaCategorias().subscribe((result: Respuesta) => {
      if (result) {
          this.listaCategorias = result.resultado;
          console.log('Lista de Categorias: ' + JSON.stringify(this.listaCategorias));
    }});
  }
}
