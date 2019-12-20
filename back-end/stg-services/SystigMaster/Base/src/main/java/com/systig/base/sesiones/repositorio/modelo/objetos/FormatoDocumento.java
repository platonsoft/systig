package com.systig.base.sesiones.repositorio.modelo.objetos;

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
