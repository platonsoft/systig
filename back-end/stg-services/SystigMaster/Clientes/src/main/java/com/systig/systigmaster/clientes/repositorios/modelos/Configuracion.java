package com.systig.systigmaster.clientes.repositorios.modelos;

import lombok.Data;

@Data
public class Configuracion {
    private Long idConfiguracion;
    private Long idPropietario;
    private String jsonConfiguracion;
}
