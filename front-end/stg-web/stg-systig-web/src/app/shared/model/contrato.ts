import { Cliente } from "./cliente";
import { Pregunta } from "./pregunta";

export interface Contrato {
    id:number;
    codigo:string;
    cliente:Cliente;
    preguntas:Pregunta[];
    fechaInicio:string;
    fechaFinal:string;
    estado:number;
}
