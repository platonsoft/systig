package com.systig.remesas;

import com.systig.remesas.modelos.Notificacion;
import com.systig.remesas.modelos.Transaccion;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

public class Inicializador implements Serializable {
    private Long imei;
    private BigDecimal tasaDia;
    private String pais;
    private List<Notificacion> notificacions;
    private List<Transaccion> transaccions;

    public Inicializador() {
    }

    @Override
    public String toString() {
        return "Inicializador{" +
                "imei=" + imei +
                ", tasaDia=" + tasaDia +
                ", pais=" + pais +
                ", notificacions=" + notificacions +
                ", transacciones=" + transaccions +
                '}';
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public BigDecimal getTasaDia() {
        return tasaDia;
    }

    public void setTasaDia(BigDecimal tasaDia) {
        this.tasaDia = tasaDia;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public List<Notificacion> getNotificacions() {
        return notificacions;
    }

    public void setNotificacions(List<Notificacion> notificacions) {
        this.notificacions = notificacions;
    }

    public List<Transaccion> getTransaccions() {
        return transaccions;
    }

    public void setTransaccions(List<Transaccion> transaccions) {
        this.transaccions = transaccions;
    }
}
