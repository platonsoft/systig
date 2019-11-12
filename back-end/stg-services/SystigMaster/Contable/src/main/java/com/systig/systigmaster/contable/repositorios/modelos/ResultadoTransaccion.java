package com.systig.systigmaster.contable.repositorios.modelos;

import lombok.Data;

@Data
public class ResultadoTransaccion {
    private String token;
    private Object resultado;
}