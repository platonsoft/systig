package com.systig.base.sesiones.repositorio.modelo.objetos;

import lombok.Data;

@Data
public class ResultadoTransaccion {
    private String token;
    private Object resultado;
}
