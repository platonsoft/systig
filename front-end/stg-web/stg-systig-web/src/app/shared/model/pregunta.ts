import { Servicio } from "./servicio";

export interface Pregunta {
    id:number;
    pregunta:string;
    descripcion:string;
    icono:string;
    aprobado:boolean;
    base:boolean;
    multisel:boolean;
    serviciosCollection:Servicio[];
}
