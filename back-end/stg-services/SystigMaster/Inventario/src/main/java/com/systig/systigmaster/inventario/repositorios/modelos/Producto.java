package com.systig.systigmaster.inventario.repositorios.modelos;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "STG_INV_PRODUCTOS")
public class Producto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long idProducto;
    private String codigo;
    private String nombre;
    private String descripcion;
    private String unidad;
    private BigDecimal impuesto;
    private BigDecimal descuento;
    private BigDecimal cantidadMinima;
    private BigDecimal cantidadOptima;
    private BigDecimal cantidadExistencia;
    private BigDecimal monto;
    private String modelo;
    @JoinColumn(name = "id_categoria", referencedColumnName = "idCategoria", nullable = false)
    @ManyToOne()
    private Categoria categoria;
    @JoinColumn(name = "id_almacen", referencedColumnName = "idAlmacen", nullable = false)
    @ManyToOne()
    private Almacen almacen;
    @JoinColumn(name = "id_proveedor", referencedColumnName = "idProveedor", nullable = false)
    @ManyToOne()
    private Proveedor proveedor;
    @JoinColumn(name = "id_propietario", referencedColumnName = "idPropietario", nullable = false)
    @ManyToOne()
    private Propietario propietario;
    private Boolean disponible;

    @OneToMany( mappedBy = "idProducto", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<ItemProducto> itemsProductos;

}
