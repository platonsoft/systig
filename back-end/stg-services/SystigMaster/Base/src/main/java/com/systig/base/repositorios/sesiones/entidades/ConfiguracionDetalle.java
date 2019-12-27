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
    private Long idConfiguracionDetalle;
    private Long fechaRegistro;
    private Long numeroTerminales;
    private Boolean isRetentor;

    @OneToMany( mappedBy = "idFormatoDocumento", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<FormatoDocumento> formatoDocumento = new ArrayList<>();

}
