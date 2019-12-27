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
}
