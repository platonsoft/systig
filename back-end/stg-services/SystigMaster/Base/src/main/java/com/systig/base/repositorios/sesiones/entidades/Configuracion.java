package com.systig.base.repositorios.sesiones.entidades;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "STG_CONFIGURACION")
public class Configuracion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idConfiguracion;
    private Long idPropietario;
    private String urlTablero;
    private String urlInventario;
    private String urlContable;
    private String urlClientes;
    private String urlProveedores;
    private String urlSesiones;

    @JoinColumn(name = "id_configuracion_detalle", referencedColumnName = "idConfiguracionDetalle", nullable = false)
    @OneToOne()
    private ConfiguracionDetalle jsonConfiguracion;
}
