package com.stg.flashpay.datos;

public class TransaccionFlashPay {
    private String fecha;
    private String uidEmisor;
    private String uidReceptor;
    private String tipo;
    private String montoSaldo;
    private String montoOperacion;

    public TransaccionFlashPay() {
    }

    public TransaccionFlashPay(String fecha, String uidEmisor, String uidReceptor, String tipo, String montoSaldo, String montoOperacion) {
        this.fecha = fecha;
        this.uidEmisor = uidEmisor;
        this.uidReceptor = uidReceptor;
        this.tipo = tipo;
        this.montoSaldo = montoSaldo;
        this.montoOperacion = montoOperacion;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUidEmisor() {
        return uidEmisor;
    }

    public void setUidEmisor(String uidEmisor) {
        this.uidEmisor = uidEmisor;
    }

    public String getUidReceptor() {
        return uidReceptor;
    }

    public void setUidReceptor(String uidReceptor) {
        this.uidReceptor = uidReceptor;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMontoSaldo() {
        return montoSaldo;
    }

    public void setMontoSaldo(String montoSaldo) {
        this.montoSaldo = montoSaldo;
    }

    public String getMontoOperacion() {
        return montoOperacion;
    }

    public void setMontoOperacion(String montoOperacion) {
        this.montoOperacion = montoOperacion;
    }
}
