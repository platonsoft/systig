import { Component, OnInit, Inject } from '@angular/core';
import { MatDialogRef, MAT_DIALOG_DATA } from '@angular/material';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Observable } from 'rxjs';
import { startWith, map } from 'rxjs/operators';
import { Proveedor, Productos, FormaPago, Envios, Respuesta, Documento } from 'app/shared/objetos';
import { ProveedoresService } from 'app/stg-proveedores/proveedores.service';
import { ProductosService } from 'app/stg-productos/productos.service';

@Component({
  selector: 'stg-documento-dlg-edit',
  templateUrl: './documento-dlg-edit.component.html',
  styleUrls: ['./documento-dlg-edit.component.scss']
})
export class DocumentoDlgEditComponent implements OnInit {

  stateCtrl = new FormControl();
  filteredProveedores: Observable<Proveedor[]>;
  filteredProveedoresOriginal: Proveedor[];
  listaProveedores: Proveedor[];
  listaProductosProveedor: Productos[] = [];
  myGroup: FormGroup;
  proveedorSelect: Proveedor = {idProveedor: 0};
  formaPago: FormaPago = {idFormaPago: 0, forma: 'CONTADO', nroCuotas: 1, tipoMonto: 'DETAL'};

  conGastosEnvio = false;
  conImpuestosAdicionales = false;
  conDescuentosAdicionales = false;

  gastoEnvio: Envios = {idEmpresaEnvios: 0};
  totalPagar = 0;

  constructor(public dialogRef: MatDialogRef<DocumentoDlgEditComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any,
              public proveedoresService: ProveedoresService,
              public productosService: ProductosService) {

    this.myGroup = new FormGroup({
      numeroIdentificacion: new FormControl('', [
        Validators.required,
      ]),
      razonSocial: new FormControl('', [
        Validators.required
      ]),
      cantidadExistencia: new FormControl(),
      conGastoEnvio: new FormControl(),
      conImpuestosAdicionales: new FormControl(),
      conDescuentosAdicionales: new FormControl(),
      cantidadOptima: new FormControl('', [
        Validators.required
      ]),
    });

    this.proveedoresService.getListaProveedores().subscribe((resultado: Respuesta) => {
      this.listaProveedores = resultado.resultado;
      console.log('Lista proveedor: ' + JSON.stringify(this.listaProveedores));
    });
  }

  ngOnInit() {
    console.log('Lista proveedor: ' + JSON.stringify(this.myGroup.get('numeroIdentificacion').valueChanges));
    this.filteredProveedores = this.myGroup.get('numeroIdentificacion').valueChanges
      .pipe(
        startWith(''),
        map(state => state ? this._filterStates(state) : this.listaProveedores.slice()
      ));
  }

  private _filterStates(value: string): Proveedor[] {
    const filterValue = value.toLowerCase();

    return this.listaProveedores.filter(state => state.razonSocial.toLowerCase().indexOf(filterValue) === 0);
  }

  cargaDetallesProveedor(proveedor: Proveedor){
    this.proveedorSelect = proveedor;
    this.gastoEnvio = proveedor.envios;
    this.productosService.getListaProductosProveedor(proveedor.idProveedor).subscribe((resultado: Respuesta) => {
      this.listaProductosProveedor = resultado.resultado;
      console.log('Lista productos por proveedor: ' + JSON.stringify(this.listaProductosProveedor));
    });
  }

  mostrarOpcion(opc: number){
    if (opc === 1) {
      this.conGastosEnvio = !this.conGastosEnvio;
    }
  }

  calcularMontoProducto(producto: Productos): number{
    return producto.montoCompra * producto.cantidadExistencia;
  }

  calcularTotalPagar() {
    let totalPagarTemp = 0;
    let totalIVATemp = 0;
    let totalSubTemp = 0;
    const ivaTemp = 19;
    this.listaProductosProveedor.forEach( function(precio: Productos) {
      totalSubTemp += (+precio.montoCompra) * (precio.cantidadExistencia);
      if (!precio.isExcento) {
        totalIVATemp += (((+precio.montoCompra) * (precio.cantidadExistencia)) * +ivaTemp) / 100;
      }
    });

    totalPagarTemp = +totalIVATemp + totalSubTemp;

    if(this.conGastosEnvio){
      totalPagarTemp += this.calcularGastosEnvios().totalEnvio;
    }

    return {subTotal: totalSubTemp, totalIVA: totalIVATemp, totalPagar: totalPagarTemp};
  }

  calcularGastosEnvios() {
    let totalPagarPesoTemp = 0;
    let totalPagarEmpaqueTemp = 0;
    let totalPagarEnvio = 0;
    const montoPeso = this.gastoEnvio.precioPeso;
    const montoEmpaque = this.gastoEnvio.precioEmpaque;

    this.listaProductosProveedor.forEach( function(precio: Productos) {
      totalPagarPesoTemp += (+precio.peso) * (+precio.cantidadExistencia) * (+montoPeso);
      totalPagarEmpaqueTemp += (+precio.altura) * (+precio.anchura) * (+precio.profundidad)
                               * (+precio.cantidadExistencia) * (+montoEmpaque);
      console.log('ResultadoOperacionEnvio' + precio.anchura + '-' + precio.altura + '-' + precio.profundidad);
    });

    totalPagarEnvio = (+totalPagarEmpaqueTemp) + (+totalPagarPesoTemp);

    return {
      costoPeso: totalPagarPesoTemp,
      costoEmpaque: totalPagarEmpaqueTemp,
      totalEnvio: totalPagarEnvio
    };
  }

  onNoClick(): void {
    this.dialogRef.close();
  }

  // convenience getter for easy access to form fields
  get f() { return this.myGroup.controls; }


  onSubmit() {
    // stop here if form is invalid
    //if (this.myGroup.invalid) {
      //console.log('Invalidado');
      //return;
   // }

    const documento: Documento = {
      idDocumento: null,
      idPropietario: this.proveedorSelect.idProveedor,
      Estado: 0,
      tipoDocumento: 4,
    };

    this.data.documento = documento;
    this.data.productos = this.listaProductosProveedor;

    console.log('Antes Documento:  ' + JSON.stringify(this.data.documento));
    console.log('Antes Productos:  ' + JSON.stringify(this.data.productos));

  }
}
