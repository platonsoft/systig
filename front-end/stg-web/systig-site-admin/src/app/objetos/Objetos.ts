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

export interface Productos {
  id: number;
  codigo: string;
  descripcion: string;
  existencia?: number;
  unidad?: number;
  impuesto?: number;
  descuento?: number;
  cantidad: number;
  monto?: number;
  categoria?: string;
  almacen?: string;
  proveedor_id?: number;
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

export class ProductoItem implements Productos {
  id = 0;
  codigo = '';
  descripcion = '';
  cantidad = 0;

}

export class ClienteItem {
  id = 0;
  tipoIdentificacion = '';
  numeroIdentificacion = '';
  identificacion = '';
  nombres = '';
  apellidos = '';
  razonSocial = '';
  tipoCliente = '';
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

export const PRODUCTOS_DATA: ProductoItem[] = [
  {
    id: 1,
    codigo: '000001',
    descripcion: 'Producto de muestra 1',
    cantidad: '1/60',
  },
  {
    id: 2,
    codigo: '000002',
    descripcion: 'Producto de muestra 2',
    cantidad: '30/50',
  },
  {
    id: 3,
    codigo: '000003',
    descripcion: 'Producto de muestra 3',
    cantidad: '15/15',
  },
];

export const CLIENTES_DATA: ClienteItem[] = [
  {
    id: 1,
    tipoIdentificacion: 'NIT',
    numeroIdentificacion: '0000000000',
    identificacion: 'NIT - 0000000000',
    nombres: '',
    apellidos: '',
    razonSocial: 'Empresa de prueba 1',
    tipoCliente: 'EMPRESA',
  },
  {
    id: 2,
    tipoIdentificacion: 'CC',
    numeroIdentificacion: '123123123',
    identificacion: 'CC - 123123123',
    nombres: 'Nombre 1',
    apellidos: 'Apellidos 1',
    razonSocial: 'Apellidos 1, Nombre 1',
    tipoCliente: 'PERSONA',
  },
  {
    id: 3,
    tipoIdentificacion: 'CC',
    numeroIdentificacion: '120120120',
    identificacion: 'CC - 120120120',
    nombres: 'Nombre 2',
    apellidos: 'Apellidos 2',
    razonSocial: 'Apellidos 2, Nombre 2',
    tipoCliente: 'PERSONA',
  },
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

