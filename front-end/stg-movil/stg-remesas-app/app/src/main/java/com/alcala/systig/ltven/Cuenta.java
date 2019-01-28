package com.alcala.systig.ltven;

import java.time.LocalDateTime;

public class Cuenta {
    private Long id_cuenta;
    /*
     * ESP: Cuenta destinada para depositos de las transacciones
     * CLI: Cuentas de los Clientes
     * */
    private String codigo;
    private String logotipo;
    private String nombreBanco;
    private String nroCuenta;
    private String tipoCuenta;
    private String pais;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaActualizacion;

    public Cuenta() {
    }

    public Long getId_cuenta() {
        return id_cuenta;
    }

    public void setId_cuenta(Long id_cuenta) {
        this.id_cuenta = id_cuenta;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getLogotipo() {
        return logotipo;
    }

    public void setLogotipo(String logotipo) {
        this.logotipo = logotipo;
    }

    public String getNombreBanco() {
        return nombreBanco;
    }

    public void setNombreBanco(String nombreBanco) {
        this.nombreBanco = nombreBanco;
    }

    public String getNroCuenta() {
        return nroCuenta;
    }

    public void setNroCuenta(String nroCuenta) {
        this.nroCuenta = nroCuenta;
    }

    public String getTipoCuenta() {
        return tipoCuenta;
    }

    public void setTipoCuenta(String tipoCuenta) {
        this.tipoCuenta = tipoCuenta;
    }

    public String getPais() {
        return pais;
    }

    public void setPais(String pais) {
        this.pais = pais;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaActualizacion() {
        return fechaActualizacion;
    }

    public void setFechaActualizacion(LocalDateTime fechaActualizacion) {
        this.fechaActualizacion = fechaActualizacion;
    }

    @Override
    public String toString() {
        return "Cuenta{" +
                "id_cuenta=" + id_cuenta +
                ", codigo='" + codigo + '\'' +
                ", logotipo='" + logotipo + '\'' +
                ", nombreBanco='" + nombreBanco + '\'' +
                ", nroCuenta='" + nroCuenta + '\'' +
                ", tipoCuenta='" + tipoCuenta + '\'' +
                ", pais='" + pais + '\'' +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaActualizacion=" + fechaActualizacion +
                '}';
    }
}
