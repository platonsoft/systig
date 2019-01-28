import { Contrato } from "./contrato";

export interface Cliente {
    id:number;
    codigo:string;
    rif:string;
    razonSocial:string;
    direccion:string;
    email:string;
    telMovil:string;
    nacionalidad:string;
    moneda:string;
    contratos:Contrato[];
}
