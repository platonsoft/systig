package com.systig.systigmaster.inventario.repositorios.modelos;

import lombok.Data;

@Data
public class ResultadoTransaccion {
    private String token;
    private Object resultado;
}
