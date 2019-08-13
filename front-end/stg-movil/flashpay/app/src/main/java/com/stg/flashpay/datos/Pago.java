package com.stg.flashpay.datos;

import java.math.BigDecimal;

public class Pago {
    private String nroCuenta;
    private BigDecimal monto;

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }
}
