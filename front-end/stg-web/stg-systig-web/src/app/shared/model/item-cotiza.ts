export interface ItemCotiza {
    id:number;
    codigo:string;
    icono?:string;
    titulo:string;
    caracteristicas?:string;
    dependencia?:number;
    tipoCampo:string;
    factorComplex:number;
    multiSelect?:boolean;
    selected?:boolean;
    serviciosHasEmpleadosList?:any;
    clientesHasServ?:any;
}
