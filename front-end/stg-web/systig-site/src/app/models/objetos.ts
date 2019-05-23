

interface ItemSplash {
  id: number;
  icono?: string;
  titulo: string;
  subTitulo: string;
  descripcion?: string;
  imagen: string;
  linkMas: string;
}

interface ItemCotizacion {
  id: number;
  imagen: string;
  titulo: string;
  descripcionCorta: string;
  descripcionLarga: string;
  seleccionado: boolean;
  disponible: boolean;
}

interface UsuarioCotizacion {
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
}

interface EtapaCotizacion {
  id: number;
  titulo: string;
  descripcionCorta: string;
  descripcionLarga: string;
  items: ItemCotizacion[];
  seleccionUnica: boolean;
  indice: number;
  esFinal: boolean;
}

interface CotizacionGeneral {
  id: number;
  codigo: string;
  usuario?: UsuarioCotizacion;
  etapas: EtapaCotizacion[];
  etapaActual: number;
  totalDescuentos: number;
  subtotal: number;
  total: number;
}

interface ProductoDestacado {
  id: number;
  titulo: string;
  subTitulo: string;
  descripcionCorta: string;
  descripcionLarga: string;
  imagen: string;
}

interface ProductoGeneral {
  id: number;
  titulo: string;
  subTitulo: string;
  descripcionCorta: string;
  descripcionLarga: string;
  imagen: string;
}

interface EmpleadosSystig {
  id: number;
  nombres: string;
  apellidos: string;
  descripcion: string;
  cargo: string;
  habilidades: string[];
  foto: string;
}

interface TrabajosRealizados{
    id: number;
    cliente: string;
    descripcion: string;
    fecha: string;
}

interface PaisDisponible{
  id: number;
  code: string;
  nombre: string;
}
