export interface ItemSplash {
  id: number;
  icono?: string;
  titulo: string;
  subTitulo: string;
  descripcion?: string;
  imagen: string;
  linkMas: string;
};

export interface ItemCotizacion {
  id: number;
  imagen: string;
  titulo: string;
  descripcionCorta: string;
  descripcionLarga: string;
  seleccionado: boolean;
  disponible: boolean;
};

export interface UsuarioCotizacion {
  id: number;
  tipo: string;
  razonSocial?: string;
  tipoDocumento?: string;
  nroDocumento?: string;
  nombres: string;
  apellidos: string;
  sexo?: string;
  email: string;
  telefLocal: string;
  telefMovil: string;
  pais: string;
};

export interface EtapaCotizacion {
  id: number;
  titulo: string;
  descripcionCorta: string;
  descripcionLarga: string;
  items: ItemCotizacion[];
  seleccionUnica: boolean;
  indice: number;
  esFinal: boolean;
};

export interface CotizacionGeneral {
  id: number;
  codigo: string;
  usuario?: UsuarioCotizacion;
  etapas: EtapaCotizacion[];
  etapaActual: number;
  totalDescuentos: number;
  subtotal: number;
  total: number;
};

export interface ProductoDestacado {
  id: number;
  titulo: string;
  subTitulo: string;
  descripcionCorta: string;
  descripcionLarga: string;
  imagen: string;
};

export interface ProductoGeneral {
  id: number;
  titulo: string;
  subTitulo: string;
  descripcionCorta: string;
  descripcionLarga: string;
  imagen: string;
};

export interface EmpleadosSystig {
  id: number;
  nombres: string;
  apellidos: string;
  descripcion: string;
  cargo: string;
  habilidades: string[];
  foto: string;
};

export interface TrabajosRealizados{
    id: number;
    cliente: string;
    descripcion: string;
    fecha: string;
};

export interface PaisDisponible{
  id: number;
  code: string;
  nombre: string;
};

export interface RespuestaWS {
  resultado: any;
  token: string;
};
