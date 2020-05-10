package com.systig.base.repositorios.sesiones.entidades;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "STG_PRODUCTOS_STG")
public class ProductoSystig {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_producto_systig")
    private Long idProductoSystig;
    private String nombre;
    private String descripcion;
    private String caracteristicas;
    private String tipo;
    private BigDecimal costoMes;
    private Boolean disponible;
    private String imagenX64;
}
