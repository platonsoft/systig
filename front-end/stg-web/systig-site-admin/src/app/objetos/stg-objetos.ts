import { DataSource } from '@angular/cdk/table';
import { BehaviorSubject, Observable } from 'rxjs';
import { ProveedoresService } from '../stg-proveedores/proveedores.service';
import { ProductosService } from '../stg-productos/productos.service';
import { ContabilidadService } from '../stg-contabilidad/contabilidad.service';
import { ClientesService } from '../stg-clientes/clientes.service';

export interface Cliente{

}

export interface Categoria{

}

export interface Almacen{

}

export interface Pais{
  numericCode: string;
  currencies: Currencies[];
}

export interface Etapa{

}

export interface CampanaPublicidad{

}

export interface Respuesta{
  resultado?: any;
}

export interface Currencies{
  name: string;
  code: any;
}

export interface Propietario{

}

export interface Proveedor{
  idProveedor?: number;
  tipoIdentificacion?: string;
  razonSocial?: string;
  pais?: string;
  envios?: Envios;
}

export interface Envios{
  idEmpresaEnvios?: number;
  precioEmpaque?: number;
  precioPeso?: number;
}

export interface Productos{
  idProducto?: number;
  profundidad?: number;
  altura?: number;
  anchura?: number;
  cantidadExistencia?: number;
  peso?: number;
  categoria?: Categoria;
  almacen?: Almacen;
  idProveedor?: number;
  montoCompra?: number;
  isExcento?: boolean;
}

export interface FormaPago {

}

export interface Historial{

}

export interface Documento{

}

export const PRODUCTOS_HISTORIAL_DATA: Historial[] = [];


export class ProveedoresDataSource extends DataSource<Proveedor> {
  /** Stream of data that is provided to the table. */
  data = new BehaviorSubject<Proveedor[]>([]);

  constructor(public proveedoresService: ProveedoresService){
    super();
    proveedoresService.getListaProveedores().subscribe((result: Respuesta) => {
      this.data = new BehaviorSubject<Proveedor[]>(result.resultado);
    });
  }

  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<Proveedor[]> {
    return this.data;
  }

  disconnect() {}
}

export class ProductosDataSource extends DataSource<Productos> {
  /** Stream of data that is provided to the table. */
  data = new BehaviorSubject<Productos[]>([]);

  constructor(public productosService: ProductosService){
    super();
    productosService.getListaProductos().subscribe((result: Respuesta) => {
      this.data = new BehaviorSubject<Productos[]>(result.resultado);
    });
  }

  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<Productos[]> {
    return this.data;
  }

  disconnect() {}
}


export class DocumentosDataSource extends DataSource<Documento> {
  /** Stream of data that is provided to the table. */
  data = new BehaviorSubject<Documento[]>([]);

  constructor(public contabilidadService: ContabilidadService){
    super();
    contabilidadService.getListaDocumentos().subscribe((result: Respuesta) => {
      this.data = new BehaviorSubject<Documento[]>(result.resultado);
    });
  }

  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<Documento[]> {
    return this.data;
  }

  disconnect() {}
}

export class ClientesDataSource extends DataSource<Cliente> {
  /** Stream of data that is provided to the table. */
  data = new BehaviorSubject<Cliente[]>([]);

  constructor(public clientesService: ClientesService){
    super();
    clientesService.getListaClientes().subscribe((result: Respuesta) => {
      this.data = new BehaviorSubject<Cliente[]>(result.resultado);
    });
  }

  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<Cliente[]> {
    return this.data;
  }

  disconnect() {}
}
