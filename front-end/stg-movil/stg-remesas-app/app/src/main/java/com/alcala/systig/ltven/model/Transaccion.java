package com.alcala.systig.ltven.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaccion {
    private Long idTransaccion;
    private String Codigo; //TIPO-METODO-NRO
    private Long imei;
    private Remitente otroRemitente;
    private Receptor cliente;
    private String comprobanteRemitente;
    private String comprobanteReceptor;
    private BigDecimal tasa;
    private BigDecimal montoRecibido;
    private BigDecimal montoPagado;
    private BigDecimal bitCompra;
    private BigDecimal bitVenta;
    private BigDecimal gananciaTotal;
    private BigDecimal comisioPagar;
    private BigDecimal comisionRetenida;
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaPago;

    public Transaccion() {
    }

    public Long getIdTransaccion() {
        return idTransaccion;
    }

    public void setIdTransaccion(Long idTransaccion) {
        this.idTransaccion = idTransaccion;
    }

    public String getCodigo() {
        return Codigo;
    }

    public void setCodigo(String codigo) {
        Codigo = codigo;
    }

    public Long getImei() {
        return imei;
    }

    public void setImei(Long imei) {
        this.imei = imei;
    }

    public Remitente getOtroRemitente() {
        return otroRemitente;
    }

    public void setOtroRemitente(Remitente otroRemitente) {
        this.otroRemitente = otroRemitente;
    }

    public Receptor getCliente() {
        return cliente;
    }

    public void setCliente(Receptor cliente) {
        this.cliente = cliente;
    }

    public String getComprobanteRemitente() {
        return comprobanteRemitente;
    }

    public void setComprobanteRemitente(String comprobanteRemitente) {
        this.comprobanteRemitente = comprobanteRemitente;
    }

    public String getComprobanteReceptor() {
        return comprobanteReceptor;
    }

    public void setComprobanteReceptor(String comprobanteReceptor) {
        this.comprobanteReceptor = comprobanteReceptor;
    }

    public BigDecimal getTasa() {
        return tasa;
    }

    public void setTasa(BigDecimal tasa) {
        this.tasa = tasa;
    }

    public BigDecimal getMontoRecibido() {
        return montoRecibido;
    }

    public void setMontoRecibido(BigDecimal montoRecibido) {
        this.montoRecibido = montoRecibido;
    }

    public BigDecimal getMontoPagado() {
        return montoPagado;
    }

    public void setMontoPagado(BigDecimal montoPagado) {
        this.montoPagado = montoPagado;
    }

    public BigDecimal getBitCompra() {
        return bitCompra;
    }

    public void setBitCompra(BigDecimal bitCompra) {
        this.bitCompra = bitCompra;
    }

    public BigDecimal getBitVenta() {
        return bitVenta;
    }

    public void setBitVenta(BigDecimal bitVenta) {
        this.bitVenta = bitVenta;
    }

    public BigDecimal getGananciaTotal() {
        return gananciaTotal;
    }

    public void setGananciaTotal(BigDecimal gananciaTotal) {
        this.gananciaTotal = gananciaTotal;
    }

    public BigDecimal getComisioPagar() {
        return comisioPagar;
    }

    public void setComisioPagar(BigDecimal comisioPagar) {
        this.comisioPagar = comisioPagar;
    }

    public BigDecimal getComisionRetenida() {
        return comisionRetenida;
    }

    public void setComisionRetenida(BigDecimal comisionRetenida) {
        this.comisionRetenida = comisionRetenida;
    }

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    @Override
    public String toString() {
        return "Transaccion{" +
                "idTransaccion=" + idTransaccion +
                ", Codigo='" + Codigo + '\'' +
                ", imei=" + imei +
                ", otroRemitente=" + otroRemitente +
                ", cliente=" + cliente +
                ", comprobanteRemitente='" + comprobanteRemitente + '\'' +
                ", comprobanteReceptor='" + comprobanteReceptor + '\'' +
                ", tasa=" + tasa +
                ", montoRecibido=" + montoRecibido +
                ", montoPagado=" + montoPagado +
                ", bitCompra=" + bitCompra +
                ", bitVenta=" + bitVenta +
                ", gananciaTotal=" + gananciaTotal +
                ", comisioPagar=" + comisioPagar +
                ", comisionRetenida=" + comisionRetenida +
                ", fechaRegistro=" + fechaRegistro +
                ", fechaPago=" + fechaPago +
                '}';
    }
}
