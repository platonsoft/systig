package com.systig.base.repositorios.pay.entidades;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "STG_PAY_TRANSACCIONES")
public class Transaccion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTransaccion;
    private String Codigo; //TIPO-METODO-NRO
    private String idRemitente;
    private String idCliente;
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
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
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
    private String metodo;
    private String nroReferencia;
    private String x64Recibo;
    private Long idPaisDestino;
}
