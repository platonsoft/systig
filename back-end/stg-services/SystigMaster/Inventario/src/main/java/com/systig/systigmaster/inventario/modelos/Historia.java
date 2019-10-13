package com.systig.systigmaster.inventario.modelos;

import lombok.Data;

@Data
public class Historia {
    private Long id;
    private String elemento;
    private String descripcion;
    private String operacion;
    private Long fecha;
}
