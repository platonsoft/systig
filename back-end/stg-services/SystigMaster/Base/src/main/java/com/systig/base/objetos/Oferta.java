package com.systig.base.objetos;

import lombok.Data;

import java.math.BigDecimal;
@Data
public class Oferta {
    private BigDecimal montoAccion;
    private BigDecimal montoMin;
    private BigDecimal montoMax;
    private BigDecimal precioBTC;
    private BigDecimal precioUSD;
    private String nombreBanco;
    private String linkAcceso;
    private String moneda;
    private BigDecimal perfilScore;
    private BigDecimal perfilNroTransacciones;
}
