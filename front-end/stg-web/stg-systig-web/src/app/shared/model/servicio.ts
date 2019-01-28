import { Pregunta } from "./pregunta";

export interface Servicio {
    id:number;
    codigo:string;
    titulo:string;
    descripcion:string;
    caracteristicas:string;
    icono:string;
    tipoElemento:number;
    valor:boolean;
    nextPregunta:Pregunta;
}
