package com.systig.systigmaster.proveedores.repositorios.modelos;

import lombok.Data;

@Data
public class ResultadoTransaccion {
    private String token;
    private Object resultado;
}
