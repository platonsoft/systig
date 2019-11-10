package com.systig.systigmaster.clientes.repositorios.modelos;

import lombok.Data;

@Data
public class ResultadoTransaccion {
    private String token;
    private Object resultado;
}
