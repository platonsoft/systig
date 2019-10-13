package com.systig.systigmaster.inventario.modelos;

import lombok.Data;

@Data
public class Almacen {
    private Long id;
    private String nombre;
    private String descripcion;
    private String tipo;
    private String ubicacion;
    private String username;
}
