package com.stg.systigpay.objetos;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;

public class TransaccionSystig {
    private Long idTransaccion;
    private Integer nroReferencia;
    private TipoTransaccionSystig tipoTransaccion;
    private Usuario idEmisor;
    private Usuario idReceptor;
    private String descripcion;
    private String observacion;
    private Date fechaTransaccion;
    private Date fechaAnulacion;
    private BigDecimal monto;
    private BigDecimal comision;
    private EntidadBancaria entidadBancaria;
    private BigInteger nroCuentaBancaria;
    private Long tipoCuentaBancaria;
    private Status estatus;


    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public Integer getNroReferencia() {
        return nroReferencia;
    }

    public void setNroReferencia(Integer nroReferencia) {
        this.nroReferencia = nroReferencia;
    }

    public TipoTransaccionSystig getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(TipoTransaccionSystig tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Usuario getIdEmisor() {
        return idEmisor;
    }

    public void setIdEmisor(Usuario idEmisor) {
        this.idEmisor = idEmisor;
    }

    public Usuario getIdReceptor() {
        return idReceptor;
    }

    public void setIdReceptor(Usuario idReceptor) {
        this.idReceptor = idReceptor;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }

    public Date getFechaAnulacion() {
        return fechaAnulacion;
    }

    public void setFechaAnulacion(Date fechaAnulacion) {
        this.fechaAnulacion = fechaAnulacion;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public BigDecimal getComision() {
        return comision;
    }

    public void setComision(BigDecimal comision) {
        this.comision = comision;
    }

    public EntidadBancaria getEntidadBancaria() {
        return entidadBancaria;
    }

    public void setEntidadBancaria(EntidadBancaria entidadBancaria) {
        this.entidadBancaria = entidadBancaria;
    }

    public BigInteger getNroCuentaBancaria() {
        return nroCuentaBancaria;
    }

    public void setNroCuentaBancaria(BigInteger nroCuentaBancaria) {
        this.nroCuentaBancaria = nroCuentaBancaria;
    }

    public Long getTipoCuentaBancaria() {
        return tipoCuentaBancaria;
    }

    public void setTipoCuentaBancaria(Long tipoCuentaBancaria) {
        this.tipoCuentaBancaria = tipoCuentaBancaria;
    }

    public Status getEstatus() {
        return estatus;
    }

    public void setEstatus(Status estatus) {
        this.estatus = estatus;
    }
}
