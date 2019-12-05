import { DataSource } from '@angular/cdk/table';
import { ProductosService } from '../stg-productos/productos.service';
import { Observable, of } from 'rxjs';
import { map } from 'rxjs/operators';
import { ClientesService } from '../stg-clientes/clientes.service';
import { ProveedoresService } from '../stg-proveedores/proveedores.service';
import { ContabilidadService } from '../stg-contabilidad/contabilidad.service';

export interface EvolucionProfileItem {
  id: number;
  titulo: string;
  descripcion: string;
  institucion: string;
  emailInstitucion: string;
  telefonoInstitucion: string;
  duracion: number;
  fechaInicio: Date;
  fechaFinal: Date;
  isEnCurso: boolean;
}

export interface Historial {
  id: number;
  accion: string;
  idProducto: number;
  descripcion: string;
  fecha: string;
}

export class FormacionItem implements EvolucionProfileItem {
  id = 0;  titulo = '';
  descripcion = '';
  institucion = '';
  emailInstitucion = '';
  telefonoInstitucion = '';
  duracion = 0;
  fechaInicio = new Date();
  fechaFinal = new Date();
  isEnCurso = false;
}

/* Objetos para Modulo de Productos */

export interface Categoria {
  idCategoria: number;
  nombre?: string;
  descripcion?: string;
  idPadre?: number;
  idPropietario?: number;
}

export interface Almacen {
  idAlmacen: number;
  nombre?: string;
  descripcion?: string;
  tipo?: number;
  ubicacion?: string;
  idPropietario?: number;
}

export interface ItemsProductos {
  idItemProducto: number;
  idDocumento?: number;
  serial?: string;
  unidad?: string;
  cantidad?: number;
  monto?: number;
}

export interface Productos {
    idProducto: number;
    codigo?: string;
    nombre?: string;
    descripcion?: string;
    unidad?: string;
    impuesto?: number;
    descuento?: number;
    cantidadMinima?: number;
    cantidadOptima?: number;
    cantidadExistencia?: number;
    montoCompra?: number;
    montoUnicoDetal?: number;
    montoUnicoMayor?: number;
    montoCuotasMayor?: number;
    montoCuotasDetal?: number;
    isExcento?: boolean;
    peso?: number;
    altura?: number;
    anchura?: number;
    profundidad?: number;
    modelo?: number;
    categoria?: Categoria;
    almacen?: Almacen;
    idProveedor?: number;
    idPropietario?: number;
    itemsProductos?: ItemsProductos[];
}

export interface FormaPago {
  idFormaPago: number;
  tipoMonto: string;
  forma: string;
  nroCuotas: number;
}

export interface Cliente {
  idComprador: number;
  tipoCliente?: number;
  tipoIdentificacion?: string;
  numeroIdentificacion?: string;
  nombres?: string;
  apellidos?: string;
  razonSocial?: string;

  telefonoLocal?: string;
  telefonoMovil?: string;
  email?: string;
  direccionFiscal?: string;
  codigoPostal?: number;
  ciudad?: string;
  provincia?: string;
  pais?: string;
  moneda?: string;

  etapa?: any;
  campanaPublicidad?: any;
  ranking?: number;
}

export interface Etapa {
  idEtapa: number;
  nombre?: string;
  descripcion?: string;
}

export interface Impuesto {
  idImpuesto: number;
  nombre?: string;
  descripcion?: string;
  isLegal?: boolean;
  porcentaje?: number;
}

export interface Descuento {
  idDescuento: number;
  nombre?: string;
  descripcion?: string;
  fechaDesde?: number;
  fechaHasta?: number;
  porcentaje?: number;
}

export interface CampanaPublicidad {
  idCampana: number;
  titulo?: string;
  descripcion?: string;
  idPropietario?: number;
  alcance?: string;
  ciudad?: string;
  provincia?: string;
  pais?: string;
  username?: string;
  validaDesde?: number;
  validaHasta?: number;
}

export interface Envios {
  idEmpresaEnvios: number;
  observaciones?: string;
  precioEmpaque?: number;
  precioPeso?: number;
}

export interface Proveedor {
  idProveedor: number;
  tipoIdentificacion?: string;
  numeroIdentificacion?: string;
  nombres?: string;
  apellidos?: string;
  razonSocial?: string;
  telefonoLocal?: string;
  telefonoMovil?: string;
  email?: string;
  webSite?: string;
  direccionFiscal?: string;
  codigoPostal?: string;
  ciudad?: string;
  provincia?: string;
  isRetentor?: boolean;
  envios?: Envios;
  pais?: string;
}

export interface Pais {
  name?: string;
  alpha2Code?: string;
  region?: string;
  subregion?: string;
  nativeName?: string;
  numericCode?: string;
  currencies?: Currencies[];
  flag?: string;
}

export interface Currencies {
  code?: string;
  name?: string;
  symbol?: string;
}

export interface Documento{
  idDocumento: number;
  tipoDocumento?: number;
  nroControl?: string;
  Codigo?: string;
  idCliente?: number;
  fecha?: string;
  validez?: number;
  idPropietario?: number;
  Estado?: number;
}

export class ExperienciAItem implements EvolucionProfileItem {
  id = 0;  titulo = '';
  descripcion = '';
  institucion = '';
  emailInstitucion = '';
  telefonoInstitucion = '';
  duracion = 0;
  fechaInicio = new Date();
  fechaFinal = new Date();
  isEnCurso = false;
}

export const FORMACION_DATA: FormacionItem[] = [
  {
    id: 1,
    titulo: 'Pre grado',
    descripcion: 'Descripcion 1',
    institucion: 'Institucion 1',
    emailInstitucion: 'email@Institucion1.com',
    telefonoInstitucion: '555-555-55',
    duracion: 300,
    fechaInicio: new Date(),
    fechaFinal: new Date(),
    isEnCurso: false,
  },
  {
    id: 2,
    titulo: 'Post Grado',
    descripcion: 'Descripcion 2',
    institucion: 'Institucion 1',
    emailInstitucion: 'email@Institucion2.com',
    telefonoInstitucion: '555-555-55',
    duracion: 300,
    fechaInicio: new Date(),
    fechaFinal: new Date(),
    isEnCurso: false,
  },
  {
    id: 3,
    titulo: 'Maestria',
    descripcion: 'Descripcion 3',
    institucion: 'Institucion 1',
    emailInstitucion: 'email@Institucion3.com',
    telefonoInstitucion: '555-555-55',
    duracion: 300,
    fechaInicio: new Date(),
    fechaFinal: new Date(),
    isEnCurso: false,
  },
];

export const PRODUCTOS_DATA: Productos[] = [
  {
    idProducto: 1,
    codigo: '000001',
    nombre: 'Producto de muestra 1',
    descripcion: 'Producto de muestra 1',
    unidad: 'Kg',
    cantidadMinima: 1,
    cantidadExistencia: 16,
  },
  {
    idProducto: 2,
    codigo: '000002',
    nombre: 'Producto de muestra 2',
    descripcion: 'Producto de muestra 2',
    unidad: 'Kg',
    cantidadMinima: 1,
    cantidadExistencia: 16,
  },
  {
    idProducto: 3,
    codigo: '000003',
    nombre: 'Producto de muestra 3',
    descripcion: 'Producto de muestra 3',
    unidad: 'UND',
    cantidadMinima: 1,
    cantidadExistencia: 16,
  },
];

export const PRODUCTOS_HISTORIAL_DATA: Historial[] = [
  {
    id: 0,
    accion: 'Creacion de Producto',
    idProducto: 1,
    descripcion: 'Creacion de Producto',
    fecha: '24/02/2019',
  },
  {
    id: 0,
    accion: 'Edicio de Producto',
    idProducto: 1,
    descripcion: 'Cambio la descripcion',
    fecha: '27/02/2019',
  }
];

export const EXPERIENCIA_DATA: ExperienciAItem[] = [
  {
    id: 1,
    titulo: 'Pre grado',
    descripcion: 'Descripcion 1',
    institucion: 'Institucion 1',
    emailInstitucion: 'email@Institucion1.com',
    telefonoInstitucion: '555-555-55',
    duracion: 300,
    fechaInicio: new Date(),
    fechaFinal: new Date(),
    isEnCurso: false,
  },
  {
    id: 2,
    titulo: 'Post Grado',
    descripcion: 'Descripcion 2',
    institucion: 'Institucion 2',
    emailInstitucion: 'email@Institucion2.com',
    telefonoInstitucion: '555-555-55',
    duracion: 300,
    fechaInicio: new Date(),
    fechaFinal: new Date(),
    isEnCurso: false,
  },
  {
    id: 3,
    titulo: 'Maestria',
    descripcion: 'Descripcion 3',
    institucion: 'Institucion 3',
    emailInstitucion: 'email@Institucion3.com',
    telefonoInstitucion: '555-555-55',
    duracion: 300,
    fechaInicio: new Date(),
    fechaFinal: new Date(),
    isEnCurso: false,
  },
];

export interface Respuesta {
  token: string;
  resultado: any;
}

export class ProductosDataSource extends DataSource<any> {
  PRODUCTOS_DATA: Productos[];

  constructor(private productoService: ProductosService) {
    super();
  }
  connect(): Observable<Productos[]> {
    return this.productoService.getListaProductos().pipe(
      map((resp: Respuesta) => {
        this.PRODUCTOS_DATA = resp.resultado;
        localStorage.setItem('tokenSystig', resp.token);
        return this.PRODUCTOS_DATA;
      }));
  }
  disconnect() {}
}

export class ClientesDataSource extends DataSource<any> {
  CLIENTES_DATA: Cliente[];

  constructor(private clienteService: ClientesService) {
    super();
  }
  connect(): Observable<Cliente[]> {
    return this.clienteService.getListaClientes().pipe(
      map((resp: Respuesta) => {
        this.CLIENTES_DATA = resp.resultado;
        localStorage.setItem('tokenSystig', resp.token);
        return this.CLIENTES_DATA;
      }));
  }
  disconnect() {}
}


export class ProveedoresDataSource extends DataSource<any> {
  PROVEEDORES_DATA: Proveedor[];

  constructor(private proveedoresService: ProveedoresService) {
    super();
  }
  connect(): Observable<Proveedor[]> {
    return this.proveedoresService.getListaProveedores().pipe(
      map((resp: Respuesta) => {
        this.PROVEEDORES_DATA = resp.resultado;
        localStorage.setItem('tokenSystig', resp.token);
        return this.PROVEEDORES_DATA;
      }));
  }
  disconnect() {}
}


export class DocumentosDataSource extends DataSource<any> {
  DOCUMENTOS_DATA: Documento[];

  constructor(private contabilidadService: ContabilidadService) {
    super();
  }
  connect(): Observable<Documento[]> {
    return this.contabilidadService.getListaDocumentos().pipe(
      map((resp: Respuesta) => {
        this.DOCUMENTOS_DATA = resp.resultado;
        localStorage.setItem('tokenSystig', resp.token);
        return this.DOCUMENTOS_DATA;
      }));
  }
  disconnect() {}
}
