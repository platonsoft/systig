package com.systig.base.repositorios.inventario.entidades;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.systig.base.repositorios.contable.entidades.Documento;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "STG_INV_ITEM_PRODUCTOS")
public class ItemProducto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idItemProducto;

    @JoinColumn(name = "id_documento", referencedColumnName = "id_documento")
    @ManyToOne()
    private Documento idDocumento;

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
    private Boolean isPublico;
    private String observacionEliminado;

    @JoinColumn(name = "id_producto", referencedColumnName = "id_producto")
    @ManyToOne()
    @JsonBackReference
    private Producto idProducto;
}
