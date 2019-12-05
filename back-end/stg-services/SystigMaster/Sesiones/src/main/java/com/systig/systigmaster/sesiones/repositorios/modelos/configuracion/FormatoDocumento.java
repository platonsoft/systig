package com.systig.systigmaster.sesiones.repositorios.modelos.configuracion;

import lombok.Data;

import java.math.BigInteger;

@Data
public class FormatoDocumento {
    private Long tipoDocumento;
    private String denominacionDocumento;
    private BigInteger nroControlBase;
    private BigInteger nroControlFin;
    private Long nroFormato;
    private String resolucionDian;
}
