import { DataSource } from '@angular/cdk/table';
import { BehaviorSubject, Observable } from 'rxjs';
import { ProveedoresService } from '../stg-proveedores/proveedores.service';
import { ProductosService } from '../stg-productos/productos.service';
import { ContabilidadService } from '../stg-contabilidad/contabilidad.service';
import { ClientesService } from '../stg-clientes/clientes.service';


export interface DatosUsuario {
  tipoIdentificacion?: TipoDocumento;
  numeroIdentificacion?: string;
  fechaNacimiento?: Date;
  email?: string;
  nombres?: string;
  apellidos?: string;
  direccion?: string;
  telefMovil?: string;
  ciudad?: string;
  pais?: string;
  codigoPostal?: string;
}

export interface TipoDocumento {
  abrev?: string;
  nombre?: string;
}

export interface Empresa {
  idEmpresa?: number;
  tipoIdentificacion?: TipoDocumento;
  nroIdentificacion?: string;
  razonSocial?: string;
  email?: string;
  website?: string;
  direccion?: string;
  telefFijo?: string;
  ciudad?: string;
  pais?: string;
  codigoPostal?: string;
  isDefault?: boolean;
}

export interface Cliente {
  idCliente?: number;
  idComprador?: number;
  tipoIdentificacion?: TipoDocumento;
  numeroIdentificacion?: string;
  fechaNacimiento?: Date;
  email?: string;
  nombres?: string;
  apellidos?: string;
  razonSocial?: string;
  direccion?: string;
  telefFijo?: string;
  telefMovil?: string;
  ciudad?: string;
  pais?: string;
  codigoPostal?: string;
  isProspecto?: boolean
}

export interface Categoria {
  idCategoria?: number;
  nombre?: string;

}

export interface Almacen {
  idAlmacen?: number;
  nombre?: string;
}

export interface Pais {
  numericCode?: string;
  currencies?: Currencies[];
  name?: string;
}

export interface Etapa {
  idEtapa: number;

}

export interface CampanaPublicidad {
  idCampanaPublicidad: number;
}

export interface Respuesta {
  resultado?: any;
}

export interface Currencies {
  name: string;
  code: any;
}

export interface Propietario {
  idPropietario?: number;
}

export interface Proveedor {
  idProveedor?: number;
  tipoIdentificacion?: string;
  razonSocial?: string;
  pais?: string;
  envios?: Envios;
}

export interface Envios {
  idEmpresaEnvios?: number;
  precioEmpaque?: number;
  precioPeso?: number;
  observaciones?: string;
}

export interface Productos {
  idProducto?: number;
  nombre?: string;
  descripcion?: string;
  unidad?: string;
  profundidad?: number;
  altura?: number;
  anchura?: number;
  cantidadExistencia?: number;
  cantidadMinima?: number;
  cantidadOptima?: number;
  peso?: number;
  categoria?: Categoria;
  almacen?: Almacen;
  idProveedor?: number;
  montoCompra?: number;
  montoUnicoDetal?: number;
  montoUnicoMayor?: number;
  montoCuotasDetal?: number;
  montoCuotasMayor?: number;
  descuento?: number;
  impuesto?: number;
  isExcento?: boolean;
  disponible?: boolean;
}

export interface FormaPago {
  idFormaPago?: number;
  forma?: string;
  nroCuotas?: number;
  tipoMonto?: string;
}

export interface Historial {
  idHistorial?: number;
}

export interface Documento {
  idDocumento?: number;
  idPropietario?: number;
  tipoDocumento?: number;
  Estado?: number;
}

export const PRODUCTOS_HISTORIAL_DATA: Historial[] = [];


export class ProveedoresDataSource extends DataSource<Proveedor> {
  /** Stream of data that is provided to the table. */
  data = new BehaviorSubject<Proveedor[]>([]);

  constructor(public proveedoresService: ProveedoresService) {
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

  constructor(public productosService: ProductosService) {
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

  constructor(public contabilidadService: ContabilidadService) {
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

  constructor(public clientesService: ClientesService) {
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

const EMPRESAS_TEST: Empresa[] = [
  {idEmpresa: 0,
  tipoIdentificacion: {
    abrev: 'RIF',
    nombre: 'Registro Informacion Fiscal'
  },
  nroIdentificacion: '10879686-6',
  razonSocial: 'SYSTIG ALCALA FP'
}
];

export class EmpresasDataSource extends DataSource<Empresa> {
  /** Stream of data that is provided to the table. */
  data = new BehaviorSubject<Empresa[]>(EMPRESAS_TEST);

  constructor() {
    super();
  }

  /** Connect function called by the table to retrieve one stream containing the data to render. */
  connect(): Observable<Empresa[]> {
    return this.data;
  }

  disconnect() {}
}
