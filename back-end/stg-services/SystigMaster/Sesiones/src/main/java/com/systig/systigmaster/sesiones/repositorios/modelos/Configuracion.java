package com.systig.systigmaster.sesiones.repositorios.modelos;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_CONFIGURACION")
public class Configuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idConfiguracion;
    private Long idPropietario;
    private String jsonConfiguracion;
}
