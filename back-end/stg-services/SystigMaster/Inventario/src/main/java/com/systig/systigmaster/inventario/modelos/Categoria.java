package com.systig.systigmaster.inventario.modelos;

import lombok.Data;

@Data
public class Categoria {
    private Long id;
    private String nombre;
    private String descripcion;
    private Categoria padre;
    private String username;
}
