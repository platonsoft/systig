package com.systig.base.repositorios.sesiones.entidades;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "STG_CONFIGURACION_DETALLE")
public class ConfiguracionDetalle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_configuracion_detalle")
    private Long idConfiguracionDetalle;
    private Long fechaRegistro;
    private Long numeroTerminales;
    private Boolean isRetentor;
}
