package com.systig.systigmaster.inventario.modelos;

import lombok.Data;

@Data
public class Historial {
    private Long id;
    private String accion;
    private Long idProducto;
    private String descripcion;
    private Long fecha;
}
