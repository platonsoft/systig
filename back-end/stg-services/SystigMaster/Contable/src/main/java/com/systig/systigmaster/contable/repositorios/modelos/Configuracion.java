package com.systig.systigmaster.contable.repositorios.modelos;

import lombok.Data;

@Data
public class Configuracion {
    private Long idConfiguracion;
    private Long idPropietario;
    private String urlInventario = "http://localhost:8090";
    private String urlContable = "http://localhost:8093";
    private String urlClientes = "http://localhost:8091";
    private String urlProveedores = "http://localhost:8092";
    private String urlSesiones = "http://localhost:8096";
    private String jsonConfiguracion;
}
