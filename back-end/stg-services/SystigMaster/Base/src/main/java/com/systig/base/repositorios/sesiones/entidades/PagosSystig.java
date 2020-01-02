package com.systig.base.repositorios.sesiones.entidades;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "STG_PAGOS_STG")
public class PagosSystig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idPagosSystig;
    private String formaPago;
    private String referencia;
    private Long fechaPago;
    private Long fechaVencimiento;
    private BigDecimal montoPagado;
    private Long estado;

    @JoinColumn(name = "id_producto_systig_x_propietario", referencedColumnName = "idProductoSystigXPropietario")
    @ManyToOne()
    @JsonIgnore
    private ProductoSystigXPropietario productoSystigXPropietario;

    private String observaciones;
}
