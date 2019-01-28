package com.systig.remesas.modelos;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idTransaccion;
    private String Codigo; //TIPO-METODO-NRO
    private Long imei;
    @OneToOne
    private Remitente otroRemitente;
    @OneToOne
    private Receptor cliente;
    private String comprobanteRemitente;
    private String comprobanteReceptor;
    @Column(columnDefinition="Decimal(10,3) default '0.000'")
    private BigDecimal tasa;
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal montoRecibido;
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal montoPagado;
    @Column(columnDefinition="Decimal(12,10) default '0.00'")
    private BigDecimal bitCompra;
    @Column(columnDefinition="Decimal(12,10) default '0.00'")
    private BigDecimal bitVenta;
    @Column(columnDefinition="Decimal(10,3) default '0.00'")
    private BigDecimal gananciaTotal;
    @Column(columnDefinition="Decimal(10,3) default '0.00'")
    private BigDecimal comisioPagar;
    @Column(columnDefinition="Decimal(10,3) default '0.00'")
    private BigDecimal comisionRetenida;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;
    private LocalDateTime fechaPago;
    /*
    G: Transaccion Enviada (Hecha por Usuario)
    I: Transaccion Depositada (Hecha por Usuario)
    L: Transaccion En Bitcoin (Hecha por Administrador)
    B: Transaccion Cambio Completa (Hecha por Administrador)
    E: Transaccion solicitada para pago (Hecha por Usuario)
    R: Transaccion Completa y Solo Pagada la comision (Hecha por Administrador),
    T: Transaccion completa y Solo cobrada (Hecha por Administrador)
    O: Transaccion Completada y cobrada (Hecha por Administrador)
    X: Transaccion Cancelada (Solo si no ha sido depositada) Hecha por usuario
    */
    private String status = "E";

    private String paisDestino;

    public Transaccion() {
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
                ", comisionPagar=" + comisioPagar+
                ", comisionRetenida=" + comisionRetenida+
                ", fechaRegistro=" + fechaRegistro +
                ", fechaPago=" + fechaPago +
                ", status='" + status + '\'' +
                ", paisDestino='" + paisDestino + '\'' +
                '}';
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPaisDestino() {
        return paisDestino;
    }

    public void setPaisDestino(String paisDestino) {
        this.paisDestino = paisDestino;
    }
}
