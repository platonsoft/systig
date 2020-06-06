package com.systig.base.repositorios.pay.entidades;

import com.systig.base.repositorios.contable.entidades.Documento;
import com.systig.base.repositorios.contable.entidades.FormaPago;
import com.systig.base.repositorios.contable.entidades.MetodoPago;
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
    @Column(name = "id_transaccion")
    private Long idTransaccion;
    private String codigo; //TIPO-METODO-NRO
    private Long idPersona;
    @Column(columnDefinition="Decimal(10,3) default '0.000'")
    private BigDecimal tasa;
    @Column(columnDefinition="Decimal(10,3) default '0.000'")
    private BigDecimal tasaReal;
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal montoRecibido;
    @Column(columnDefinition="Decimal(10,2) default '0.00'")
    private BigDecimal montoPagado;
    @Column(columnDefinition="Decimal(20,10) default '0.00'")
    private BigDecimal bitCompra;
    @Column(columnDefinition="Decimal(20,10) default '0.00'")
    private BigDecimal bitVenta;
    @Column(columnDefinition="Decimal(20,10) default '0.00'")
    private BigDecimal gananciaTotal;
    @Column(columnDefinition="Decimal(20,10) default '0.00'")
    private BigDecimal comisioPagar;
    @Column(columnDefinition="Decimal(10,3) default '0.00'")
    private BigDecimal comisionRetenida;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaRegistro;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaPago;
    /*
    G: Transaccion Enviada (Hecha por Usuario) temporal
    I: Transaccion Realizada Completa (Hecha por Administrador)
    L: Transaccion En Bitcoin (Hecha por Administrador) temporal
    B: Transaccion Cambio Completa (Hecha por Administrador)
    E: Transaccion solicitada para pago (Hecha por Usuario) temporal
    R: Transaccion Completa y Solo Pagada la comision (Hecha por Administrador),
    T: Transaccion completa y Solo cobrada (Hecha por Administrador)
    O: Transaccion Completada y cobrada (Hecha por Administrador)
    X: Transaccion Cancelada (Solo si no ha sido depositada) Hecha por usuario
    */
    private String status = "E";
    private String descripcion;
    private String nroReferencia;
    private String x64Recibo;
    private Long idPaisDestino;
    private String refOperacion;

    @JoinColumn(name = "documento", referencedColumnName = "id_documento")
    @ManyToOne()
    private Documento documento;

    @JoinColumn(name = "forma_pago", referencedColumnName = "id_forma_pago")
    @ManyToOne()
    private FormaPago formaPago;

    @JoinColumn(name = "metodo_pago", referencedColumnName = "id_metodo_pago")
    @ManyToOne()
    private MetodoPago metodoPago;

    private Long usuarioConfirmacion;
    @Column(columnDefinition="TIMESTAMP default CURRENT_TIMESTAMP")
    private LocalDateTime fechaConfirmacion;

    private String linkCompra;
    private String linkVenta;
}
