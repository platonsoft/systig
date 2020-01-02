package com.systig.base.repositorios.sesiones.entidades;

import lombok.Data;

import javax.persistence.*;
import java.math.BigInteger;

@Data
@Entity
@Table(name = "STG_FORMATO_DOCUMENTO")
public class FormatoDocumento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idFormatoDocumento;
    private Long tipoDocumento;
    private String denominacionDocumento;
    private BigInteger nroControlBase;
    private BigInteger nroControlFin;
    private Long nroFormato;
    private String resolucionDian;

    @JoinColumn(name = "id_configuracion_detalle", referencedColumnName = "id_configuracion_detalle", nullable = false)
    @ManyToOne()
    private ConfiguracionDetalle configuracionDetalle;
}
