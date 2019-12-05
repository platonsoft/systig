package com.systig.systigmaster.proveedores.repositorios.modelos;

import lombok.Data;

@Data
public class Configuracion {
    private Long idConfiguracion;
    private Long idPropietario;
    private String jsonConfiguracion;
}
