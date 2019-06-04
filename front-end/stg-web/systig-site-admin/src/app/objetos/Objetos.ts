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

