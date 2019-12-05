package com.systig.systigmaster.inventario.repositorios.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "STG_INV_ITEM_PRODUCTOS")
public class ItemProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idItemProducto;
    private Long idDocumento;
    private String serial;
    private String unidad;
    private BigDecimal cantidad;
    private BigDecimal montoVenta;
    private BigDecimal montoCompra;
    private BigDecimal montoImpuesto;
    private BigDecimal montoDescuento;
    private Long cicloVida;
    private Long eliminado;
    private Long fechaExpedicion;
    private Long fechaVencimiento;
    private Long idEmpresaEnvios;
    private String observacionEliminado;
    @JoinColumn(name="idProducto")
    @ManyToOne(fetch = FetchType.LAZY, optional=false, cascade = CascadeType.ALL, targetEntity = Producto.class)
    @JsonBackReference
    private Producto idProducto;
}
