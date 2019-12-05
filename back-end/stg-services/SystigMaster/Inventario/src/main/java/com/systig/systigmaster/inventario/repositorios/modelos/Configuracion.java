package com.systig.systigmaster.inventario.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
public class Configuracion {
    private Long idConfiguracion;
    private Long idPropietario;
    private String jsonConfiguracion;
}
